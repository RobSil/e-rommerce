package com.robsil.erommerce.service.impl;

import com.robsil.erommerce.data.domain.Vendor;
import com.robsil.erommerce.data.domain.VendorEmployeePermission;
import com.robsil.erommerce.model.VendorEmployeePosition;
import com.robsil.erommerce.model.vendor.employee.permission.VendorEmployeePermissionName;
import com.robsil.erommerce.service.VendorEmployeePermissionService;

import java.util.ArrayList;
import java.util.List;

public class VendorEmployeePermissionServiceImpl implements VendorEmployeePermissionService {

    public VendorEmployeePermission saveEntity(VendorEmployeePermission vep) {

    }

    @Override
    public VendorEmployeePermission findByVendorId(String vendorId) {
        return null;
    }

    @Override
    public VendorEmployeePermission init(Vendor vendor) {

        var vep = new VendorEmployeePermission();

        vep.setVendorId(vendor.getId());

        var permissions = vep.getPermissions();

        permissions.put(VendorEmployeePermissionName.REMOVE_MANAGER, new ArrayList<>(List.of(VendorEmployeePosition.OWNER)));
        permissions.put(VendorEmployeePermissionName.RAISE_TO_MANAGER, new ArrayList<>(List.of(VendorEmployeePosition.OWNER)));

//        vep.getCanDeleteProduct().add(VendorEmployeePosition.OWNER);
//        vep.getCanDeleteProduct().add(VendorEmployeePosition.MANAGER);
//        vep.getCanManageProduct().add(VendorEmployeePosition.OWNER);
//        vep.getCanManageProduct().add(VendorEmployeePosition.MANAGER);
//        vep.getCanCreateProduct().add(VendorEmployeePosition.OWNER);
//        vep.getCanCreateProduct().add(VendorEmployeePosition.MANAGER);
//        vep.getCanAddManagers().add(VendorEmployeePosition.OWNER);
//        vep.getCanRemoveManagers().add(VendorEmployeePosition.OWNER);
//        vep.getCanAddWorkers().add(VendorEmployeePosition.OWNER);
//        vep.getCanAddWorkers().add(VendorEmployeePosition.MANAGER);
//        vep.getCanRemoveWorkers().add(VendorEmployeePosition.OWNER);
//        vep.getCanRemoveWorkers().add(VendorEmployeePosition.MANAGER);
//        vep.getCanRaiseToManager().add(VendorEmployeePosition.OWNER);
//        vep.getCanGetOrderInformation().add(VendorEmployeePosition.OWNER);
//        vep.getCanGetOrderInformation().add(VendorEmployeePosition.MANAGER);
//        vep.getCanGetOrderInformation().add(VendorEmployeePosition.WORKER);

        vep = saveEntity(vep);

        return vep;
    }
}
