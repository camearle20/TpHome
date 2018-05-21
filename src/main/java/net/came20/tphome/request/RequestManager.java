package net.came20.tphome.request;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class RequestManager {
    private static ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
    private static LinkedList<Request> queue = new LinkedList<>();

    private static class TimeoutTask implements Runnable {
        private Request request;

        public TimeoutTask(Request request) {
            this.request = request;
        }

        @Override
        public void run() {
            removeAndCancel(request);
            request.timeout();
        }
    }

    private static Queue<Request> getRequestsByAsked(Player asked) {
        LinkedList<Request> subList = new LinkedList<>();
        for (Request request : queue) {
            if (request.getAsked().equals(asked)) {
                subList.add(request);
            }
        }
        return subList;
    }

    private static Queue<Request> getRequestsByAsker(Player asker) {
        LinkedList<Request> subList = new LinkedList<>();
        for (Request request : queue) {
            if (request.getAsker().equals(asker)) {
                subList.add(request);
            }
        }
        return subList;
    }

    private static void removeAndCancel(Request request) {
        queue.remove(request);
        request.cancelTimeout();
    }

    synchronized public static void registerAndSend(Request request) {
        if (!queue.contains(request)) {
            queue.add(request);
            request.send();
            request.setTimeout(executor.schedule(new TimeoutTask(request), request.getTimeoutTime(), TimeUnit.SECONDS));
        } else {
            request.asker.sendMessage(ChatColor.RED + "You have already made this request!");
        }
    }

    synchronized public static void handleAccept(Player player) {
        Queue<Request> requests = getRequestsByAsked(player);
        Request request = requests.poll();
        if (request != null) {
            removeAndCancel(request);
            request.accept();
        } else {
            player.sendMessage(ChatColor.RED + "You don't have any requests.");
        }
    }

    synchronized public static void handleDecline(Player player) {
        Queue<Request> requests = getRequestsByAsked(player);
        Request request = requests.poll();
        if (request != null) {
            removeAndCancel(request);
            request.decline();
        } else {
            player.sendMessage(ChatColor.RED + "You don't have any requests.");
        }
    }

    synchronized public static void handleCancel(Player player) {
        if (player != null) {
            Queue<Request> requests = getRequestsByAsker(player);
            Request request = requests.poll();
            if (request != null) {
                removeAndCancel(request);
                request.cancel();
            } else {
                player.sendMessage(ChatColor.RED + "You haven't made any requests.");
            }
        }
    }
}
