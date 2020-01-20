public class Channel {

    private String channelName;

    private String customerName;

    private String channelRating;

    private int id;

    private String category;

    public Channel() {
    }

    public Channel(String channelName, String customerName, String channelRating, int id, String category) {
        this.channelName = channelName;
        this.customerName = customerName;
        this.channelRating = channelRating;
        this.id = id;
        this.category = category;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getChannelRating() {
        return channelRating;
    }

    public void setChannelRating(String channelRating) {
        this.channelRating = channelRating;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
