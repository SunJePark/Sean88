package sytem.blog;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class Blog {
    private final HashMap<String, String> userList = new HashMap<String, String>(); // nickname / Id
    private final ArrayList<Post> POST_LIST;
    private final OffsetDateTime CREATED_AT;
    private OffsetDateTime modifiedAt;
    private final String BLOG_ID = UUID.randomUUID().toString();
    private final String BLOGGER_ID;
    private final String USER_KEYCHAIN = UUID.randomUUID().toString();
    private boolean isTagFilterOn;
    private boolean isAuthorFilterOn;
    private PostOrder postOrderStatus;
    private String searchingTag;
    private String searchingAuthor;

    public Blog(String userId) {
        if (userId == null) {
            throw new IllegalArgumentException("Parameter can't be null!");
        }
        this.userList.put(userId, this.USER_KEYCHAIN);
        this.POST_LIST = new ArrayList<Post>();
        this.BLOGGER_ID = userId;
        this.CREATED_AT = OffsetDateTime.now();
        this.modifiedAt = this.CREATED_AT;
        this.isTagFilterOn = false;
        this.isAuthorFilterOn = false;
        this.postOrderStatus = PostOrder.DESCENDING_CREATED_DATE;
        this.searchingTag = "NO_TAG";
        this.searchingAuthor = this.BLOGGER_ID;
    }

    public String getUserId() {
        return this.BLOGGER_ID;
    }

    public void setTagFilter(String tag) {
        if (tag == null || tag == "") {
            return;
        }
        this.isTagFilterOn = true;
        this.searchingTag = tag;
        this.modifiedAt = OffsetDateTime.now();
    }

    public void setAuthorFilter(String author) {
        if (author == null) {
            return;
        }
        this.isAuthorFilterOn = true;
        this.searchingAuthor = author;
        this.modifiedAt = OffsetDateTime.now();
    }

    public void setAuthorFilter() {
        this.isAuthorFilterOn ^= this.isTagFilterOn;
        this.modifiedAt = OffsetDateTime.now();
    }

    public void setTagFilter() {
        this.isTagFilterOn ^= this.isTagFilterOn;
        this.modifiedAt = OffsetDateTime.now();
    }

    public void setPostOrder(int sortingType) {
        if (sortingType < 0 || sortingType > 5) {
            return;
        }
        switch (sortingType) {
            case 1:
                this.postOrderStatus = PostOrder.DESCENDING_CREATED_DATE;
                break;
            case 2:
                this.postOrderStatus = PostOrder.ASCENDING_CREATED_DATE;
                break;
            case 3:
                this.postOrderStatus = PostOrder.DESCENDING_MODIFIED_DATE;
                break;
            case 4:
                this.postOrderStatus = PostOrder.ASCENDING_MODIFIED_DATE;
                break;
            case 5:
                this.postOrderStatus = PostOrder.ASCENDING_DICTIONARY_TITLE;
                break;
            default:
                break;
        }
        this.modifiedAt = OffsetDateTime.now();
    }

    public String getPostList() {
        ArrayList<Post> result = new ArrayList<Post>();
        if (this.POST_LIST.size() == 0 || this.POST_LIST == null) {
            return new String();
        }
        if (this.isTagFilterOn == true) {
            for (int i = 0; i < this.POST_LIST.size(); ++i) {
                ArrayList<String> tagsList = this.POST_LIST.get(i).getPostTags();
                for (int j = 0; j < tagsList.size(); ++j) {
                    if (tagsList.get(j) == this.searchingTag) {
                        result.add(this.POST_LIST.get(i));
                        break;
                    }
                }
            }
        }
        if (this.isAuthorFilterOn == true) {
            for (int i = 0; i < this.POST_LIST.size(); ++i) {
                if (this.POST_LIST.get(i).getAuthorId() == this.searchingAuthor) {
                    result.add(this.POST_LIST.get(i));
                }
            }
        }
        if (this.isTagFilterOn == false && this.isAuthorFilterOn == false) {
            result = this.POST_LIST;
            /* for (int i = 0; i < this.POST_LIST.size(); ++i) {
                result.add(this.POST_LIST.get(i));
            } */
        }

        switch (this.postOrderStatus) {
            case DESCENDING_CREATED_DATE:
                boolean isDesCreatedDateAllSorted = false;
                boolean isDesCreatedDateNeverSorted = true;
                while (isDesCreatedDateAllSorted == false) {
                    isDesCreatedDateNeverSorted = true;
                    for (int i = 0; i < result.size() - 1; ++i) {
                        if (result.get(i).createdBefore(result.get(i + 1))) {
                            result.add(i, result.get(i + 1));
                            result.remove(i + 2);
                            isDesCreatedDateNeverSorted = false;
                            continue;
                        }
                        if (i == result.size() - 2 && isDesCreatedDateNeverSorted == true) {
                            isDesCreatedDateAllSorted = true;
                        }
                    }
                }
                break;
            case ASCENDING_CREATED_DATE:
                boolean isAscCreatedDateAllSorted = false;
                boolean isAscCreatedDateNeverSorted = true;
                while (isAscCreatedDateAllSorted == false) {
                    isAscCreatedDateNeverSorted = true;
                    for (int i = result.size() - 1; i >= 1; --i) {
                        if (result.get(i).createdBefore(result.get(i - 1))) {
                            result.add(i - 1, result.get(i));
                            result.remove(i + 1);
                            isAscCreatedDateNeverSorted = false;
                        }
                        if (i == 1 && isAscCreatedDateNeverSorted == true) {
                            isAscCreatedDateAllSorted = true;
                        }
                    }
                }
                break;
            case DESCENDING_MODIFIED_DATE:
                boolean isDesModifiedDateAllSorted = false;
                boolean isDesModifiedDateNeverSorted = true;
                while (isDesModifiedDateAllSorted == false) {
                    isDesModifiedDateNeverSorted = true;
                    for (int i = 0; i < result.size() - 1; ++i) {
                        if (result.get(i).modifiedBefore(result.get(i + 1))) {
                            result.add(i, result.get(i + 1));
                            result.remove(i + 2);
                            isDesModifiedDateNeverSorted = false;
                            continue;
                        }
                        if (i == result.size() - 2 && isDesModifiedDateNeverSorted == true) {
                            isDesModifiedDateAllSorted = true;
                        }
                    }
                }
                break;
            case ASCENDING_MODIFIED_DATE:
                boolean isAscModifiedDateAllSorted = false;
                boolean isAscModifiedDateNeverSorted = true;
                while (isAscModifiedDateAllSorted == false) {
                    isAscModifiedDateNeverSorted = true;
                    for (int i = result.size() - 1; i >= 1; --i) {
                        if (result.get(i).modifiedBefore(result.get(i - 1))) {
                            result.add(i - 1, result.get(i));
                            result.remove(i + 1);
                            isAscModifiedDateNeverSorted = false;
                        }
                        if (i == 1 && isAscModifiedDateNeverSorted == true) {
                            isAscModifiedDateAllSorted = true;
                        }
                    }
                }
                break;
            case ASCENDING_DICTIONARY_TITLE:
                int dictionaryValueCheck;
                boolean isAllSorted = false;
                boolean isNeverSorted = true;
                while (isAllSorted == false) {
                    if (result.size() <= 1) {
                        break;
                    }
                    isNeverSorted = true;
                    for (int i = 0; i < result.size() - 1; ++i) {
                        dictionaryValueCheck = result.get(i).getTitle().compareTo(result.get(i + 1).getTitle());
                        if (dictionaryValueCheck > 0) {
                            result.add(i, result.get(i + 1));
                            result.remove(i + 2);
                            isNeverSorted = false;
                        }
                        if (i == result.size() - 2 && isNeverSorted == true) {
                            isAllSorted = true;
                        }
                    }
                }
                break;
        }
        return postListToStringArray(result);
    }

    public void addPost(Post post) {
        if (post == null) {
            throw new IllegalArgumentException("Parameters can't be null!");
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.POST_LIST.add(post);
        this.modifiedAt = OffsetDateTime.now();
    }

    public void updatePostTitle(int postId, String authorId, String title) {
        if (authorId == null || title == null) {
            throw new IllegalArgumentException("Parameters can't be null");
        }
        if (postId < 0 || postId >= this.POST_LIST.size()) {
            throw new IndexOutOfBoundsException("Index out of Bounds!");
        }
        if (authorId != this.POST_LIST.get(postId).getAuthorId() || userList.containsKey(authorId) == false) {
            return;
        }
        this.POST_LIST.get(postId).setTitle(title);
    }

    public void updatePostBody(int postId, String authorId, String body) {
        if (authorId == null || body == null) {
            throw new IllegalArgumentException("Parameters can't be null");
        }
        if (postId < 0 || postId >= this.POST_LIST.size()) {
            throw new IndexOutOfBoundsException("Index out of Bounds!");
        }
        if (authorId != POST_LIST.get(postId).getAuthorId() || userList.containsKey(authorId) == false) {
            return;
        }
        this.POST_LIST.get(postId).setBody(body);
    }

    public void addPostTag(int postId, String authorId, String tag) {
        if (this.POST_LIST.size() == 0 || authorId != this.POST_LIST.get(postId).getAuthorId() || postId < 0 || postId >= this.POST_LIST.size()) {
            return;
        }
        if (authorId == null || tag == null || this.userList.containsKey(authorId) == false) {
            return;
        }
        POST_LIST.get(postId).addTag(tag);
    }

    public void addComment(int postId, String userId, String comment) {
        if (userId == null || comment == null) {
            throw new IllegalArgumentException("Parameters can't be null");
        }
        if (this.POST_LIST.size() == 0 || postId < 0 || postId >= this.POST_LIST.size()) {
            throw new IndexOutOfBoundsException("Index out of Bounds!");
        }
        if (userId != POST_LIST.get(postId).getAuthorId()) {
            return;
        }
        if (!this.userList.containsKey(userId)) {
            this.userList.put(userId, UUID.randomUUID().toString());
        }
        this.POST_LIST.get(postId).addComment(userId, comment);
    }

    public void addSubcomment(int postId, int commentId, String userId, String subcomment) {
        if (userId == null || subcomment == null) {
            throw new IllegalArgumentException("Parameters can't be null");
        }
        if (commentId < 0 || this.POST_LIST.size() == 0) {
            return;
        }
        if (this.userList.get(userId) != userId || this.POST_LIST.get(postId).getCommentList().size() == 0) {
            return;
        }
        if (!this.userList.containsKey(userId)) {
            this.userList.put(userId, UUID.randomUUID().toString());
        }
        this.POST_LIST.get(postId).getCommentList().get(commentId).addSubcomment(userId, subcomment);
    }

    public void updateComment(int postId, int commentId, String authorId, String comment) {
        if (authorId == null || comment == null) {
            throw new IllegalArgumentException("Parameters can't be null");
        }
        if (postId < 0 || postId >= this.POST_LIST.size() || this.POST_LIST.get(postId).getCommentList().size() == 0) {
            return;
        }
        if (authorId != POST_LIST.get(postId).getAuthorId()) {
            return;
        }
        if (commentId < 0 || commentId >= POST_LIST.get(postId).getCommentList().get(commentId).getSubcommentList().size()) {
            return;
        }
        this.POST_LIST.get(postId).getCommentList().get(commentId).updateCommentBody(authorId, comment);
    }

    public void updateSubcomment(int postId, int commentId, int subcommentId, String authorId, String subcomment) {
        if (authorId == null || subcomment == null) {
            throw new IllegalArgumentException("Parameters can't be null");
        }
        if (postId < 0 || postId >= this.POST_LIST.size() || this.POST_LIST.get(postId).getCommentList().size() == 0) {
            return;
        }
        if (this.POST_LIST.size() == 0 || this.POST_LIST.get(postId).getCommentList().size() == 0 || this.POST_LIST.get(postId).getCommentList().get(commentId).getSubcommentList().size() == 0) {
            return;
        }
        if (commentId < 0 || commentId >= POST_LIST.get(postId).getCommentList().get(commentId).getSubcommentList().size()) {
            return;
        }

        if (authorId != POST_LIST.get(postId).getCommentList().get(commentId).getSubcommentList().get(subcommentId).getAuthorId()) {
            return;
        }
        this.POST_LIST.get(postId).getCommentList().get(commentId).getSubcommentList().get(subcommentId).updateCommentBody(authorId, subcomment);
    }

    public int addReaction(int postId, int emojiId, String userId) throws Exception {
        if (userId == null) {
            throw new IllegalArgumentException("Parameters can't be null!");
        }
        if (this.POST_LIST.size() == 0 || postId < 0 || postId >= this.POST_LIST.size()) {
            throw new IndexOutOfBoundsException("Index out of bounds!");
        }
        if (emojiId < 0 || emojiId > 5) {
            throw new IllegalArgumentException("The emoji Id is in range of (1~5)");
        }
        if (!this.userList.containsKey(userId)) {
            this.userList.put(userId, UUID.randomUUID().toString());
        }
        EmojiType emoji = getEmojiTypeOrNull(emojiId);
        this.POST_LIST.get(postId).addReaction(userId, emoji);

        int modifiedEmojiCount = 0;
        switch (emojiId) {
            case 1: // GREAT
                modifiedEmojiCount = this.POST_LIST.get(postId).getEmojiGreat();
                break;
            case 2: // SAD
                modifiedEmojiCount = this.POST_LIST.get(postId).getEmojiSad();
                break;
            case 3: // ANGRY
                modifiedEmojiCount = this.POST_LIST.get(postId).getEmojiAngry();
                break;
            case 4: // FUN
                modifiedEmojiCount = this.POST_LIST.get(postId).getEmojiLove();
                break;
        }

        return modifiedEmojiCount;
    }

    public void removeReaction(int postId, String userId) {
        if (userId == null) {
            throw new IllegalArgumentException("The parameters can't be null!");
        }
        if (this.POST_LIST.size() == 0 || postId < 0 || postId >= this.POST_LIST.size()) {
            return;
        }
        if (!this.userList.containsKey(userId)) {
            return;
        }
        this.POST_LIST.get(postId).removeReaction(userId);
    }

    public void upvoteComment(int postId, int commentId, String userId) {
        if (userId == null) {
            throw new IllegalArgumentException("The parameters can't be null");
        }
        if (this.POST_LIST.size() == 0 || postId < 0 || postId >= this.POST_LIST.size() || commentId < 0 || commentId >= this.POST_LIST.get(postId).getCommentList().size()) {
            return;
        }
        if (!this.userList.containsKey(userId)) {
            this.userList.put(userId, UUID.randomUUID().toString());
        }
        this.POST_LIST.get(postId).getCommentList().get(commentId).setUpvote(userId);
    }

    public void downvoteComment(int postId, int commentId, String userId) {
        if (userId == null) {
            throw new IllegalArgumentException("The parameters can't be null!");
        }
        if (this.POST_LIST.size() == 0 || postId < 0 || postId >= this.POST_LIST.size() || commentId < 0 || commentId >= this.POST_LIST.get(postId).getCommentList().size()) {
            return;
        }
        if (!this.userList.containsKey(userId)) {
            this.userList.put(userId, UUID.randomUUID().toString());
        }
        this.POST_LIST.get(postId).getCommentList().get(commentId).setDownvote(userId);
    }

    public String getSubcommentList(int postId, int commentId) { // 반환형 확인 필요
        if (this.POST_LIST.size() == 0 || postId < 0 || postId >= this.POST_LIST.size() || commentId < 0 || commentId >= this.POST_LIST.get(postId).getCommentList().size()) {
            return new String();
        }
        String convertedResult = commentListToStringArray(this.POST_LIST.get(postId).getCommentList().get(commentId).getSubcommentList());
        return convertedResult;
    }

    public void upvoteSubcomment(int postId, int commentId, int subcommentId, String userId) {
        if (this.POST_LIST.size() == 0 || postId < 0 || postId >= this.POST_LIST.size() || commentId < 0 || commentId >= this.POST_LIST.get(postId).getCommentList().size()) {
            return;
        }
        if (subcommentId < 0 || subcommentId >= this.POST_LIST.get(postId).getCommentList().get(subcommentId).getSubcommentList().size()) {
            return;
        }
        if (userId == null) {
            return;
        }
        if (!this.userList.containsKey(userId)) {
            this.userList.put(userId, UUID.randomUUID().toString());
        }
        this.POST_LIST.get(postId).getCommentList().get(commentId).getSubcommentList().get(subcommentId).setUpvote(userId);
    }

    public void downvoteSubcomment(int postId, int commentId, int subcommentId, String userId) {
        if (this.POST_LIST.size() == 0 || postId < 0 || postId >= this.POST_LIST.size() || commentId < 0 || commentId >= this.POST_LIST.get(postId).getCommentList().size()) {
            return;
        }
        if (userId == null) {
            return;
        }
        if (!this.userList.containsKey(userId)) {
            this.userList.put(userId, UUID.randomUUID().toString());
            this.POST_LIST.get(postId).getCommentList().get(commentId).getSubcommentList().get(subcommentId).setDownvote(userId);
        }
    }

    public String getCommentList(int postId) {
        if (this.POST_LIST.size() == 0 || this.POST_LIST.get(postId).getCommentList().size() == 0) {
            return new String();
        }
        if (postId < 0 || postId >= this.POST_LIST.size()) {
            throw new IllegalArgumentException("Parameter can't be out of Range!(1~5)");
        }
        ArrayList<Comment> result = new ArrayList<>();

        for (int i = 0; i < this.POST_LIST.get(postId).getCommentList().size(); ++i) {
            result.add(this.POST_LIST.get(postId).getCommentList().get(i));
        }
        result = this.POST_LIST.get(postId).getCommentListByVote();
        String convertedResult = commentListToStringArray(result);

        return convertedResult;
    }

    private EmojiType getEmojiTypeOrNull(int emojiType) {
        if (emojiType < 1 || emojiType > 5) {
            throw new IllegalArgumentException("Parameter can't be out of Range!(1~5)");
        }
        switch (emojiType) {
            case 1:
                return EmojiType.GREAT;
            case 2:
                return EmojiType.SAD;
            case 3:
                return EmojiType.ANGRY;
            case 4:
                return EmojiType.FUN;
            case 5:
                return EmojiType.LOVE;
            default:
                return null;
        }
    }

    private String postListToStringArray(ArrayList<Post> postList) {
        if (postList.size() == 0) {
            return new String();
        }
        ArrayList<String> result = new ArrayList<String>();
        for (int i = 0; i < postList.size(); ++i) {
            result.add(postList.get(i).getBody());
        }

        String convertedResult = result.toString(); //String convertedResult = result.toString();
        return convertedResult;
    }

    private String commentListToStringArray(ArrayList<Comment> commentList) {
        if (commentList.size() == 0) {
            return new String();
        }
        ArrayList<String> result = new ArrayList<String>();
        for (int i = 0; i < commentList.size(); ++i) {
            result.add(commentList.get(i).getCommentBody());
        }
        String convertedResult = result.toString();
        return convertedResult;
    }
}
