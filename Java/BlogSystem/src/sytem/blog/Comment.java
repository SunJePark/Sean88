package sytem.blog;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class Comment {
    private final HashMap<String, String> USER_LIST;
    private final HashMap<String, Integer> VOTE_USER_CHECK; // BitFlag 1.Upvote 2.Downvote
    private final ArrayList<Comment> SUBCOMMENT_LIST;
    private final OffsetDateTime CREATED_AT;
    private OffsetDateTime modifiedAt;
    private final String AUTHOR_ID;
    private String commentBody;
    private int upVote;
    private int downVote;

    public Comment(HashMap<String, String> userList, String author, String comment) {
        this.USER_LIST = userList;
        this.VOTE_USER_CHECK = new HashMap<String, Integer>();
        this.SUBCOMMENT_LIST = new ArrayList<Comment>();
        this.CREATED_AT = OffsetDateTime.now();
        this.modifiedAt = this.CREATED_AT;
        this.AUTHOR_ID = author;
        this.commentBody = comment;
        this.upVote = 0;
        this.downVote = 0;
    }

    public void addSubcomment(String userId, String comment) {
        if (!this.USER_LIST.containsKey(userId)) {
            USER_LIST.put(userId, UUID.randomUUID().toString());
            this.SUBCOMMENT_LIST.add(new Comment(this.USER_LIST, userId, comment));
        }
    }

    public void updateCommentBody(String userId, String comment) {
        if (userId != this.AUTHOR_ID || this.USER_LIST.containsKey(userId) == false) {
            return;
        }
        this.commentBody = comment;
        this.modifiedAt = OffsetDateTime.now();
    }

    public ArrayList<Comment> getSubcommentList() {
        return this.SUBCOMMENT_LIST;
    }

    public String getCommentBody() {
        return this.commentBody;
    }

    public String getAuthorId() {
        return this.AUTHOR_ID;
    }

    public void setUpvote(String userId) {
        if (!this.USER_LIST.containsKey(userId)) {
            this.USER_LIST.put(userId, UUID.randomUUID().toString());
            if (!this.VOTE_USER_CHECK.containsKey(userId)) {
                this.VOTE_USER_CHECK.put(userId, 1);
                this.upVote++;
            } else {
                if ((VOTE_USER_CHECK.get(userId) | 1) == 1) {
                    this.upVote = (VOTE_USER_CHECK.get(userId) == 1) ? upVote - 1 : upVote + 1;
                    VOTE_USER_CHECK.put(userId, (VOTE_USER_CHECK.get(userId) ^ 1));
                } else {
                    this.downVote--;
                    this.upVote++;
                    VOTE_USER_CHECK.put(userId, (VOTE_USER_CHECK.get(userId) & 1));
                }
            }

            this.modifiedAt = OffsetDateTime.now();
        }
    }

    public void setDownvote(String userId) {
        if (!this.USER_LIST.containsKey(userId)) {
            this.USER_LIST.put(userId, UUID.randomUUID().toString());
            if (!this.VOTE_USER_CHECK.containsKey(userId)) {
                this.VOTE_USER_CHECK.put(userId, 2);
                this.downVote++;
            } else {
                if ((VOTE_USER_CHECK.get(userId) | 2) == 2) {
                    this.downVote = (VOTE_USER_CHECK.get(userId) == 2) ? downVote - 1 : upVote + 1;
                    VOTE_USER_CHECK.put(userId, (VOTE_USER_CHECK.get(userId) ^ 2));
                } else {
                    this.upVote--;
                    this.downVote++;
                    VOTE_USER_CHECK.put(userId, (VOTE_USER_CHECK.get(userId) & 2));
                }
            }
            this.downVote++;

            this.modifiedAt = OffsetDateTime.now();
        }
    }

    public int getVoteSum() {
        return this.upVote - this.downVote;
    }
}
