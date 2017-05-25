package model;

import annotation.EnableYaml;

import java.util.List;

/**
 * Created by Administrator on 2017/5/24.
 */

@EnableYaml(prefix = "c.b.a.server.redis", path = "server.yaml")
public class RedisConfig {
    private String host;
    private Integer port;
    private String username;
    private String password;
    private List<String> names;
    private User user;
    private Customer customer;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<String> getNames() {
        return names;
    }

    public void setNames(List<String> names) {
        this.names = names;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "RedisConfig{" +
                "host='" + host + '\'' +
                ", port=" + port +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", names=" + names +
                ", user=" + user.toString() +
                ", customer=" + customer.toString() +
                '}';
    }
}
