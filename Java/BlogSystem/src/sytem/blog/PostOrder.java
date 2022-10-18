package sytem.blog;

public enum PostOrder {
    DESCENDING_CREATED_DATE(1),
    ASCENDING_CREATED_DATE(2),
    DESCENDING_MODIFIED_DATE(3),
    ASCENDING_MODIFIED_DATE(4),
    ASCENDING_DICTIONARY_TITLE(5);

    private int value;
    private PostOrder(int value) {
        this.value = value;
    }
}
