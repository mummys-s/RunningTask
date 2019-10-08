package cn.amphobia.running_task.Service;

import cn.amphobia.running_task.Service.impl.AddressServiceImpl;
import cn.amphobia.running_task.bean.Address;
import cn.amphobia.running_task.mapper.AddressMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService implements AddressServiceImpl {

    @Autowired
    private AddressMapper addressMapper;


    @Override
    public int addAddress(String id, String address, String status, String telephone,String username,String userTelephone) {
        return addressMapper.addAddress(id,address,status,telephone,username,userTelephone);
    }

    @Override
    public int updateAddress(String address, String username,String status, String telephone, String id) {
        return addressMapper.updateAddress(address,username,status,telephone,id);
    }

    @Override
    public List<Address> getAddress(String userTelephone) {
        return addressMapper.getAddress(userTelephone);
    }
}
