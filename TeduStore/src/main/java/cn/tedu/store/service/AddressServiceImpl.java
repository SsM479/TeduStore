package cn.tedu.store.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.tedu.store.bean.Address;
import cn.tedu.store.mapper.AddressMapper;
import cn.tedu.store.service.ex.DataNotFoundException;

@Service("addressService")
public class AddressServiceImpl implements IAddressService{
	
	@Resource
	private AddressMapper addressMapper;
	
	@Resource(name="districtService")
	private IDistrictService districtService;

	public Integer add(Address address) {
		// 由Controller提交的数据并没有recvDistrict字段
		// 此时需要得到该字段应有的值，并封装到address中
		String recvDistrict = getRecvDistrict(address);
		//address.setRecvDistrict("测试数据");
		address.setRecvDistrict(recvDistrict);
		// 确定当前收货地址是否为默认地址
		// 仅当当前收货地址是该用户的第1条时设置为默认
		List<Address> addressList = getAddressListByUid(address.getUid());
		Integer isDefault = addressList.size() == 0 ? 1 : 0;
		address.setIsDefault(isDefault);
		//执行增加
		addressMapper.insert(address);
		// 获取id
		Integer id = address.getId();
		return id;			
	}
	
	@Transactional
	public Integer delete(Integer id, Integer uid) {
		// 调整：考虑各种情景
		// 声明返回值
		Integer affectedRows = 0;
		// 获取被删除的数据的信息
		Address address = getAddressByIdAndUid(id, uid);
		// 判断查询结果是否为null
		if(address == null) {
			// 结果为null，抛出异常
			throw new DataNotFoundException("尝试删除的数据不存在，请刷新后再执行操作");
		}else {
			// 获取数据成功，即将被删除的数据此时是存在的
			// 根据已知条件执行删除
			affectedRows = addressMapper.delete(id, uid);
			// 判断操作是否成功
			if(affectedRows == 0) {
				// 删除失败
				throw new DataNotFoundException("删除默认地址失败！收货地址可能已经被删除，请刷新后再次尝试");
			} 
			// 判断刚才删除的是否为默认地址
			if(address.getIsDefault() == 1) {
				// 刚才删除的是默认地址
				// 查询该用户剩余的所有地址数据，按id顺序排列
				List<Address> addressList = addressMapper.getAddressListByUid(uid);
				// 判断这次查询的集合的长度是否大于0
				if(addressList.size() > 0) {
					// 如果查询到结果，表示还剩余其他的收货地址
					// 取第1条数据的id
					Integer newId = addressList.get(0).getId();
					// 将以上查询到的id对应的数据设为默认
					affectedRows = setDefault(newId, uid);
					// 判断操作是否成功
					if(affectedRows == 0) {
						// 设置失败
						throw new DataNotFoundException("删除成功，但是设置默认地址失败！新的默认收货地址可能已经被删除，请刷新后再次尝试");
					}
				}else {
					// 如果查询不到结果，表示经过刚才的删除后，已经没有收货地址了，直接结束
				}
			}else {
				// 刚才删除的不是默认地址，直接结束
			}
		}
		return affectedRows;
	}

	public Integer update(Address address) {
		// 获取省市区的名称
		String recvDistrict = getRecvDistrict(address);
		// 在参数中封装省市区的名称
		address.setRecvDistrict(recvDistrict);
		// 执行修改
		return addressMapper.update(address);
	}
	
	@Transactional
	public Integer setDefault(Integer id, Integer uid) {
		// 先将该用户的所有收货地址设置为非默认
		Integer affectedRows = addressMapper.cancelAllDefault(uid);
		if(affectedRows == 0) {
			throw new DataNotFoundException("设置默认地址失败，没有查询到收货地址");
		}
		// 将指定的收货地址设置为默认
		affectedRows = addressMapper.setDefault(id, uid);
		// 判断操作是否成功
		if(affectedRows == 0) {
			// 受影响的行数为0
			// 即设置默认时，这条数据已经不存在了
			// 则应该回滚事务
			throw new DataNotFoundException("设置默认失败，数据可能已经被删除，请刷新后再次尝试");
		}else {
			return affectedRows;
		}
	}
	
	public List<Address> getAddressListByUid(Integer uid) {
		return addressMapper.getAddressListByUid(uid);
	}
	
	public Address getAddressByIdAndUid(Integer id, Integer uid) {
		return addressMapper.getAddressByIdAndUid(id, uid);
	}
	
	private String getRecvDistrict(Address address) {
		String provinceCode = address.getRecvProvince(); //510000
		String provinceName = districtService.getProvinceNameByCode(provinceCode);
		String cityCode = address.getRecvCity();
		String cityName = districtService.getCityNameByCode(cityCode);
		String areaCode = address.getRecvArea();
		String areaName = districtService.getAreaNameByCode(areaCode);
		String recvDistrict = provinceName + cityName + areaName;
		return recvDistrict;
	}
	
}
