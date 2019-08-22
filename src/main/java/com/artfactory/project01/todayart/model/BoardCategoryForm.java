package com.artfactory.project01.todayart.model;

import com.artfactory.project01.todayart.entity.BoardCategory;

public class BoardCategoryForm {

    private Integer boardId;
    private String boardName;
    private BoardCategory boardCategory;

    public Integer getBoardId() {
        return boardId;
    }

    public void setBoardId(Integer boardId) {
        this.boardId = boardId;
    }

    public String getBoardName() {
        return boardName;
    }

    public void setBoardName(String boardName) {
        this.boardName = boardName;
    }

    public BoardCategory getBoardCategory() {
        return boardCategory;
    }

    public void setBoardCategory(BoardCategory boardCategory) {
        if(boardId != null){
            boardCategory.setBoardId(this.boardId);
        }
        if(boardName != null){
            boardCategory.setBoardName(this.boardName);
        }
    }
}
