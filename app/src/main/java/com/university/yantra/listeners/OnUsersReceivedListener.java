package com.university.yantra.listeners;

import com.university.yantra.models.User;

import java.util.List;

/**
 * @author Paras Jain
 * @version  1.0
 * @since 2018-02-24
 *
 * <h1>OnUsersReceivedListener</h1>
 * <p>Listens to the Users received network event. Supports reception of <b>multiple users</b> only.</p>
 */

public interface OnUsersReceivedListener {
    void onUsersReceived(List<User> users);
}
