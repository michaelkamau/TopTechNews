package mikekamau.com.toptechnews.api.schema;

import com.google.gson.annotations.Expose;

class NewsSource {
    @Expose
    private String name;

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "NewsSource{" +
                "name='" + name + '\'' +
                '}';
    }
}
