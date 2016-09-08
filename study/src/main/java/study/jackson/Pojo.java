package study.jackson;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Pojo {
	@JsonProperty
	private int id;
	@JsonProperty
	private String name;
}
