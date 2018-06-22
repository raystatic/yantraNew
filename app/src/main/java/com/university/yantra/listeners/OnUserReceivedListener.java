package com.university.yantra.listeners;

import com.university.yantra.models.User;

/**
 * @author Paras Jain
 * @version  1.0
 * @since 2018-02-24
 *
 * <h1>OnUserReceivedListener</h1>
 * <p>Listens to the User received network event. Supports reception of <b>single user</b> only.</p>
 */

public interface OnUserReceivedListener {
    void onUserReceived(User user);
}
