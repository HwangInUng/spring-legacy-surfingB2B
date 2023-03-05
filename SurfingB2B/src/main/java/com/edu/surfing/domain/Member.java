package com.edu.surfing.domain;

import lombok.Data;

@Data
public class Member {
	private int member_idx;
	private String member_id;
	private String member_pass;
	private String local_address;
	private String detail_address;
	private int zip_code;
	private int phone_no;
	private String email;
	private String regdate;
	private String profile_image;
}
