package com.smart.qna.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class PrioritizeListRequest {

    private List<PrioritizeRequest> prioritizeList = new ArrayList<>();

}
