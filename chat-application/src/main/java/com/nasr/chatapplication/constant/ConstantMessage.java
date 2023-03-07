package com.nasr.chatapplication.constant;

import lombok.Getter;

@Getter
public class ConstantMessage {


    // registration & login process
    public static final String VERIFICATION_CODE_NOT_VALID="INVALID_CODE";
    public static final String NEW_ACCOUNT="NEW_ACCOUNT";
    public static final String ALREADY_SIGNED_IN="ALREADY_SIGNED_IN";
    //-----------------------------------

    // receiver discriminator & values
    public static final String RECEIVER_TYPE ="receiver_type";
    public static final String PV_RECEIVER_VALUE="pv";
    public static final String GROUP_RECEIVER_VALUE="group";
    public static final String CHANNEL_RECEIVER_VALUE="channel";
    //-----------------------------------

    // message discriminator & values

    public static final String MESSAGE_TYPE="message_type";
    public static final String TEXT_MESSAGE_VALUE="text_message";
    public static final String VOICE_MESSAGE_VALUE="voice_message";
    public static final String FILE_MESSAGE_VALUE="file_message";

    // jwt authentication
    public static final String ACCESS_TOKEN="access_token";
    public static final String REFRESH_TOKEN="refresh_token";

    //

    // token & refresh token expiration date
    public static final Long ACCESS_TOKEN_EXPIRATION_IN_MINUTE=(long) 30 * 24 * 60 * 60;
    public static final Long REFRESH_TOKEN_EXPIRATION_IN_MINUTE=(long) 3 * 30 * 24 * 60 * 60;

    // ---------------------------
}
