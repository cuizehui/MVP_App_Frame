package com.nela.mvpdemo.data.event;

import java.util.List;

public class PermissionEvent {

    public List<String> permissions;

    public PermissionEvent(List<String> permissions) {
        this.permissions = permissions;
    }

}
