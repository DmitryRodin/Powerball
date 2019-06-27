package main.java;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;

public class PowerBallGame {
    public static void main(String[] args) {
        class Ticket {

            private UUID name;

            private List<Long> whiteBalls;

            private Long redBall;

            private Ticket(UUID uuid, ArrayList<Long> whiteBalls, Long redBall) {
                this.name = uuid;
                this.whiteBalls = whiteBalls;
                this.redBall = redBall;
            }

            @Override
            public String toString() {
                return name + " " + whiteBalls + " " + redBall;
            }
        }

        List<Ticket> tickets = new ArrayList<Ticket>();
        ArrayList<Long> winningWhiteBallsList = new ArrayList<>();
        ArrayList<Long> whiteBallsList = new ArrayList<>();
        Long WinWhiteBall1 = new Random().nextInt(69) + 1L;
        Long WinWhiteBall2 = new Random().nextInt(69) + 1L;
        Long winWhiteBall3 = new Random().nextInt(69) + 1L;
        Long winWhiteBall4 = new Random().nextInt(69) + 1L;
        Long winWhiteBall5 = new Random().nextInt(69) + 1L;
        Long winRedBall = new Random().nextInt(26) + 1L;

        Ticket winnTicket = new Ticket(null, new ArrayList<>(asList(WinWhiteBall1, WinWhiteBall2, winWhiteBall3, winWhiteBall4, winWhiteBall5)), winRedBall);

        for (int i = 0; i <= 1000; i++) {
            whiteBallsList.clear();
            Long x1 = new Random().nextInt(69) + 1L;
            Long x2 = new Random().nextInt(69) + 1L;
            Long x3 = new Random().nextInt(69) + 1L;
            Long x4 = new Random().nextInt(69) + 1L;
            Long x5 = new Random().nextInt(69) + 1L;

            Long rb = new Random().nextInt(26) + 1L;
            tickets.add(new Ticket(UUID.randomUUID(), new ArrayList<>(asList(x1, x2, x3, x4, x5)), rb));
        }

        List<Map<String, Long>> maps = tickets.stream().map(ticket -> {
            Map<String, Long> result = new HashMap<>();
            AtomicInteger qwery = new AtomicInteger(0);
            ticket.whiteBalls.forEach(ball -> winnTicket.whiteBalls.forEach(wb -> {
                if (ball.equals(wb)) {
                    qwery.addAndGet(1);
                }

            }));
            result.put("white", qwery.longValue());

            Long redBall = 0L;
            if (ticket.redBall.equals(winnTicket.redBall)) {
                redBall = 1L;
            }
            result.put("red", redBall);
            return result;
        }).collect(toList());


        List<Long> winsMoney = maps.stream().map(map -> {
            Long winMoney = 0L;
            Long red = map.get("red");
            Long white = map.get("white");

            if ((red == 1L && white == 0L) || (red == 1L && white == 1L)) {
                winMoney = 4L;
            }
            if ((red == 1L && white == 2L) || (red == 0L && white == 3L)) {
                winMoney = 7L;
            }
            if ((red == 1L && white == 3L) || (red == 0L && white == 4L)) {
                winMoney = 100L;
            }
            if (red == 1L && white == 4L) {
                winMoney = 50000L;
            }
            if (red == 0L && white == 5L) {
                winMoney = 2000000L;
            }
            if (red == 1L && white == 5L) {
                winMoney = 10000000L;
            }

            if (white == 1L && red == 0L || white == 0 && red == 0) {
                winMoney = 0L;
            }
            return winMoney;
        }).collect(toList());


        Long fourDollarsWin = winsMoney.stream().filter(i -> i.equals(4L)).count();// 0 white 1 red / 1 red 1 white
        Long sevenDollarsWin = winsMoney.stream().filter(i -> i.equals(7L)).count(); // 3 white 0 red / 2 white 1 red
        Long hundredDollarsWin = winsMoney.stream().filter(i -> i.equals(100L)).count(); // 3 white 1 red / 4 white 0 red
        Long fiftyThousandWin = winsMoney.stream().filter(i -> i.equals(50000L)).count(); // 4 white 1 red
        Long twoMillions = winsMoney.stream().filter(i -> i.equals(2000000L)).count(); //5 white balls 0 red
        Long reachAsGod = winsMoney.stream().filter(i -> i.equals(10000000L)).count(); //5 white balls 1 red
        Long loozer = winsMoney.stream().filter(i -> i.equals(0L)).count();// 0 white 1 red / 1 red 1 white


        System.out.println("Little bigger then looser. 4 USD PRIZE! Number of winners: " + "(" + fourDollarsWin + ")");
        System.out.println();
        System.out.println("7 USD PRIZE! Number of winners: " + "(" + sevenDollarsWin + ")");
        System.out.println();
        System.out.println("A piece of paper that everyone wishes. You won 100USD! Number of winners: " + "(" + hundredDollarsWin + ")");
        System.out.println();
        System.out.println("Not bad! You won 50k USD! Number of winners: " + "(" + fiftyThousandWin + ")");
        System.out.println();
        System.out.println("Just understand, that now you dont need to work rest of your life: 2 000 000 USD! Number of winners: " + "(" + twoMillions + ")");
        System.out.println();
        System.out.println("Now all of your school mates whats to be your best friends: JackPot!!! Number of winners: " + "(" + reachAsGod + ")");
        System.out.println();
        System.out.println("May be next time... Number of winners: " + "(" + loozer +")");
    }
}
