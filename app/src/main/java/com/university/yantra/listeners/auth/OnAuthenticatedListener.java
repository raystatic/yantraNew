package com.university.yantra.listeners.auth;

import com.university.yantra.apiservices.responses.AuthenticationResponse;

/**
 * @author Paras Jain
 * @version  1.0
 * @since 2018-02-24
 *
 * <h1>OnAuthenticatedListener</h1>
 * <p>Listens to the Authenticated event. To be implemented by Login activity that authenticates users</p>
 *
 *
 */

public interface OnAuthenticatedListener {
    /**
     * Callback method onAuthenticated
     */
    void onAuthenticated(AuthenticationResponse authenticationResponse);
}
