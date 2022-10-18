package sytem.blog;

public enum EmojiType {
    GREAT(1),
    SAD(2),
    ANGRY(3),
    FUN(4),
    LOVE(5);

    private int value;
    private EmojiType(int value) {
        this.value = value;
    }
}
