package com.giftlist.smtp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EmailRequest {
    private String recipient;
    private String msgBody;
    private String subject;
    private String attachment;
    private String name;
}
