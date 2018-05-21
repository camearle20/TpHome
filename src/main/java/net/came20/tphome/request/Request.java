package net.came20.tphome.request;

import org.bukkit.entity.Player;

import java.util.concurrent.ScheduledFuture;

public abstract class Request {
    protected Player asker;
    protected Player asked;
    protected long timeoutTime;
    protected ScheduledFuture timeout;

    protected Request(Player asker, Player asked, long timeoutTime) {
        this.asker = asker;
        this.asked = asked;
        this.timeoutTime = timeoutTime;
    }

    public Player getAsker() {
        return asker;
    }

    public Player getAsked() {
        return asked;
    }

    public long getTimeoutTime() {
        return timeoutTime;
    }

    void setTimeout(ScheduledFuture timeout) {
        this.timeout = timeout;
    }

    void cancelTimeout() {
        timeout.cancel(true);
    }

    abstract public void send();
    abstract public void accept();
    abstract public void decline();
    abstract public void cancel();
    abstract public void timeout();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Request request = (Request) o;

        if (asker != null ? !asker.equals(request.asker) : request.asker != null) return false;
        return asked != null ? asked.equals(request.asked) : request.asked == null;
    }

    @Override
    public int hashCode() {
        int result = asker != null ? asker.hashCode() : 0;
        result = 31 * result + (asked != null ? asked.hashCode() : 0);
        return result;
    }
}
