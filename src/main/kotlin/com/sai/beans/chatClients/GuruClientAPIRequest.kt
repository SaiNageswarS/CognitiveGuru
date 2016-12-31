package com.sai.beans.chatClients

import com.sai.beans.OriginalRequest

/**
 * Created by sainageswar on 30/12/16.
 */
class GuruClientAPIRequest (
    var email: String = "",
    var message: String = ""
) : OriginalRequest()