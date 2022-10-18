package sytem.blog;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class Post {
    private final HashMap<String, String> USER_LIST;
    private final ArrayList<Comment> COMMENT_LIST;
    private final HashMap<String, Integer> EMOJI_USER_CHECK;
    private final OffsetDateTime CREATED_AT;
    private OffsetDateTime modifiedAt;
    private final String AUTHOR_ID;
    private final String POST_ID = UUID.randomUUID().toString();
    private String title;
    private String body;
    private final ArrayList<String> postTags;
    private int emojiGreat;
    private int emojiSad;
    private int emojiAngry;
    private int emojiFun;
    private int emojiLove;


    //
    public Post(HashMap<String, String> userList, String authorId, String article) {
        this.USER_LIST = userList;
        this.COMMENT_LIST = new ArrayList<Comment>();
        this.EMOJI_USER_CHECK = new HashMap<String, Integer>();
        this.CREATED_AT = OffsetDateTime.now();
        this.modifiedAt = this.CREATED_AT;
        this.AUTHOR_ID = authorId;
        this.title = "No_TITLE";
        this.body = article;
        this.postTags = new ArrayList<String>();
        this.emojiGreat = 0;
        this.emojiSad = 0;
        this.emojiAngry = 0;
        this.emojiFun = 0;
        this.emojiLove = 0;
    }

    public void setTitle(String title) {
        this.title = (title != null) ? title : "No Name";
        this.modifiedAt = OffsetDateTime.now();
    }

    public void setBody(String body) {
        this.body = (body != null) ? body : "No body";
        this.modifiedAt = OffsetDateTime.now();
    }

    public void addTag(String tag) {
        if (tag == null) {
            return;
        }
        for (String tagFromList : this.postTags) {
            if (tagFromList == tag) {
                return;
            }
        }
        this.postTags.add(tag);
        this.modifiedAt = OffsetDateTime.now();
    }

    public String getAuthorId() {
        return this.AUTHOR_ID;
    }

    public String getTitle() {
        return this.title;
    }

    public String getBody() {
        return this.body;
    }

    public ArrayList<String> getPostTags() {
        return this.postTags;
    }

    public void addComment(String author, String comment) {
        if (!this.USER_LIST.containsKey(author)) {
            this.USER_LIST.put(author, UUID.randomUUID().toString());
            COMMENT_LIST.add(new Comment(this.USER_LIST, author, comment));
        }
    }

    public void addReaction(String userId, EmojiType emojiId) {
        if (!this.USER_LIST.containsKey(userId)) {
            this.USER_LIST.put(userId, UUID.randomUUID().toString());
        }
        if (!this.EMOJI_USER_CHECK.containsKey(userId)) { // emojiFlag : 1.GREAT, 2.SAD, 4.ANGRY, 8.FUN, 16.LOVE
            this.EMOJI_USER_CHECK.put(userId, 0);
        }
        int emojiFlag = this.EMOJI_USER_CHECK.get(userId);
        int mask = 16;
        int emojiCount = 0;
        while (true) {
            if (mask == 0) {
                break;
            } else if ((mask & emojiFlag) == 1) {
                emojiCount++;
            }
            mask = mask >> 1;
        }
        switch (emojiId) {
            case GREAT:
                if ((emojiFlag & 1) == 1) {
                    this.EMOJI_USER_CHECK.put(userId, this.EMOJI_USER_CHECK.get(userId) ^ 1);
                    this.emojiGreat--;
                    break;
                }
                if ((emojiFlag & 1) == 0 && emojiCount < 2) {
                    this.EMOJI_USER_CHECK.put(userId, this.EMOJI_USER_CHECK.get(userId) ^ 1);
                    this.emojiGreat++;
                    break;
                }
            case SAD:
                if ((emojiFlag & 2) == 2) {
                    this.EMOJI_USER_CHECK.put(userId, this.EMOJI_USER_CHECK.get(userId) ^ 2);
                    this.emojiSad--;
                    break;
                }
                if ((emojiFlag & 2) == 0 && emojiCount < 2) {
                    this.EMOJI_USER_CHECK.put(userId, this.EMOJI_USER_CHECK.get(userId) ^ 2);
                    this.emojiSad++;
                    break;
                }
            case ANGRY:
                if ((emojiFlag & 4) == 4) {
                    this.EMOJI_USER_CHECK.put(userId, this.EMOJI_USER_CHECK.get(userId) ^ 4);
                    this.emojiAngry--;
                    break;
                }
                if ((emojiFlag & 4) == 0 && emojiCount < 2) {
                    this.EMOJI_USER_CHECK.put(userId, this.EMOJI_USER_CHECK.get(userId) ^ 4);
                    this.emojiAngry++;
                    break;
                }
            case FUN:
                if ((emojiFlag & 8) == 8) {
                    this.EMOJI_USER_CHECK.put(userId, this.EMOJI_USER_CHECK.get(userId) ^ 8);
                    this.emojiFun--;
                    break;
                }
                if ((emojiFlag & 8) == 0 && emojiCount < 2) {
                    this.EMOJI_USER_CHECK.put(userId, this.EMOJI_USER_CHECK.get(userId) ^ 8);
                    this.emojiFun++;
                    break;
                }
            case LOVE:
                if ((emojiFlag & 16) == 16) {
                    this.EMOJI_USER_CHECK.put(userId, this.EMOJI_USER_CHECK.get(userId) ^ 16);
                    this.emojiLove--;
                    break;
                }
                if ((emojiFlag & 16) == 0 && emojiCount < 2) {
                    this.EMOJI_USER_CHECK.put(userId, this.EMOJI_USER_CHECK.get(userId) ^ 16);
                    this.emojiLove++;
                    break;
                }
            default:
                break;
        }
        this.modifiedAt = OffsetDateTime.now();
    }

    public void removeReaction(String userId) {
        if (!this.EMOJI_USER_CHECK.containsKey(userId)) {
            return;
        }
        int emojiBitCheck = EMOJI_USER_CHECK.get(userId);
        if ((emojiBitCheck & 1) == 1) { // unset GREAT
            this.EMOJI_USER_CHECK.put(userId, this.EMOJI_USER_CHECK.get(userId) ^ 1);
            this.emojiGreat--;
        }
        if ((emojiBitCheck & 2) == 2) { // unset SAD
            this.EMOJI_USER_CHECK.put(userId, this.EMOJI_USER_CHECK.get(userId) ^ 2);
            this.emojiSad--;
        }
        if ((emojiBitCheck & 4) == 4) { // unset ANGRY
            this.EMOJI_USER_CHECK.put(userId, this.EMOJI_USER_CHECK.get(userId) ^ 4);
            this.emojiAngry--;
        }
        if ((emojiBitCheck & 8) == 8) { // unset FUN
            this.EMOJI_USER_CHECK.put(userId, this.EMOJI_USER_CHECK.get(userId) ^ 8);
            this.emojiFun--;
        }
        if ((emojiBitCheck & 16) == 16) { // unset LOVE
            this.EMOJI_USER_CHECK.put(userId, this.EMOJI_USER_CHECK.get(userId) ^ 16);
            this.emojiLove--;
        }
        this.modifiedAt = OffsetDateTime.now();
    }

    public ArrayList<Comment> getCommentListByVote() {
        ArrayList<Comment> result = new ArrayList<>();
        for (int i = 0; i < this.COMMENT_LIST.size(); ++i) {
            result.add(COMMENT_LIST.get(i));
        }
        boolean allSorted = false;

        Comment tempData;
        while (allSorted == false) {
            boolean noSorted = false;
            for (int i = 0; i < result.size() - 1; ++i) {
                if (result.get(i).getVoteSum() < result.get(i + 1).getVoteSum()) {
                    tempData = result.get(i + 1);
                    result.remove(i + 1);
                    result.add(i, tempData);
                    noSorted = true;
                }

                if (i == result.size() - 2 && noSorted == true) {
                    allSorted = true;
                }
            }
        }

        return result;
    }

    public ArrayList<Comment> getCommentList() {
        return this.COMMENT_LIST;
    }

    public OffsetDateTime getCreatedDate() {
        return this.CREATED_AT;
    }

    public OffsetDateTime getModifiedDate() {
        return this.modifiedAt;
    }

    public boolean modifiedBefore(Post otherPost) {
        return this.modifiedAt.isBefore(otherPost.getModifiedDate());
    }

    public boolean createdBefore(Post otherPost) {
        return this.CREATED_AT.isBefore(otherPost.getCreatedDate());
    }

    public int getEmojiGreat() {
        return this.emojiGreat;
    }

    public int getEmojiSad() {
        return this.emojiSad;
    }

    public int getEmojiAngry() {
        return this.emojiAngry;
    }

    public int getEmojiFun() {
        return this.emojiFun;
    }

    public int getEmojiLove() {
        return this.emojiLove;
    }
}

