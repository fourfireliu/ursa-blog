package com.fourfire.blog.enums;

public enum ArticleInfoType {
	NO_CONTENT("无内容", 10),
    SHORT_CONTENT("部分内容", 20), 
    ALL_CONTENT("所有内容", 30);
    
    private String desc;
    public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	private int type;

    ArticleInfoType(String desc, int type) {
        this.desc = desc;
        this.type = type;
    }
}
