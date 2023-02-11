package com.robsil.erommerce.data.domain;

import com.robsil.erommerce.model.VendorEmployeePosition;
import com.robsil.erommerce.model.vendor.employee.permission.VendorEmployeePermissionName;
import com.robsil.erommerce.util.ListUtil;
import lombok.*;
import org.springframework.data.annotation.*;
import org.springframework.data.mongodb.core.index.Indexed;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VendorEmployeePermission {

    @Id
    private String id;

    @CreatedDate
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime lastModifiedDate;

    @CreatedBy
    private Principal createdBy;

    @Version
    private long version;

    @Indexed(unique = true)
    private String vendorId;

    Map<VendorEmployeePermissionName, List<VendorEmployeePosition>> permissions = new HashMap<>();

    private List<VendorEmployeePosition> canDeleteProduct = new ArrayList<>();
    private List<VendorEmployeePosition> canManageProduct = new ArrayList<>();
    private List<VendorEmployeePosition> canCreateProduct = new ArrayList<>();
    private List<VendorEmployeePosition> canAddManagers = new ArrayList<>();
    private List<VendorEmployeePosition> canRemoveManagers = new ArrayList<>();
    private List<VendorEmployeePosition> canAddWorkers = new ArrayList<>();
    private List<VendorEmployeePosition> canRemoveWorkers = new ArrayList<>();
    private List<VendorEmployeePosition> canRaiseToManager = new ArrayList<>();
    private List<VendorEmployeePosition> canGetOrderInformation = new ArrayList<>();

    public boolean havePermission(VendorEmployeePosition position, VendorEmployeePermissionName permissionName) {
        return ListUtil.contains(permissions.getOrDefault(permissionName, new ArrayList<>()), lPos -> lPos.equals(position));
    }

    public List<VendorEmployeePermissionName> getPermissions(VendorEmployeePosition position) {
        List<VendorEmployeePermissionName> result = new ArrayList<>();

        for (var entry: permissions.entrySet()) {
            if (ListUtil.contains(entry.getValue(), position::equals)) {
                result.add(entry.getKey());
            }
        }

        return result;
    }
}
