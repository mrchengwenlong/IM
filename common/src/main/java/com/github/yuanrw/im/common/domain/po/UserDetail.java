package com.github.yuanrw.im.common.domain.po;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"deleted", "gmtUpdate"})
public class UserDetail extends User {
}
