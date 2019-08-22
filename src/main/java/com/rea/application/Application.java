package com.rea.application;

import java.util.Scanner;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Stream;

import static java.lang.Integer.*;

public class Application {

    private static Position bounds = new Position(5,5);

    private static Tuple<Position, Direction> currentState = null;

    public interface RobotCommand {

        Tuple<Position, Direction> execute (String [] parts);
    }


    public enum CommandType implements RobotCommand{
        PLACE(){
            @Override
            public Tuple<Position, Direction> execute(String [] input) {

                if(input.length != 2){
                    return currentState;
                }

                String[] placeParams = input[1].split(",");

                if (placeParams.length != 3) {
                    return currentState;
                }

                if (!isInt(placeParams[0].trim()) || !isInt(placeParams[1].trim())) {
                    return currentState;
                }

                Position position = new Position(parseInt(placeParams[0].trim()), parseInt(placeParams[1].trim()));

                Direction direction = Direction.getType(placeParams[2].trim());

                if (direction == null){
                    return currentState;
                }

                return place.apply(new Tuple<>(position, direction));
            }

        },
        MOVE(){
            @Override
            public Tuple<Position, Direction> execute(String [] input) {
                return moveOne.apply(currentState);
            }
        },
        RIGHT(){
            @Override
            public Tuple<Position, Direction> execute(String [] input) {
                return rigth.apply(currentState);
            }
        },
        LEFT(){
            @Override
            public Tuple<Position, Direction> execute(String [] input) {
                return left.apply(currentState);
            }
        },
        REPORT(){
            @Override
            public Tuple<Position, Direction> execute(String [] input) {
                System.out.println(currentState.getA()+","+currentState.getB());
                return currentState;
            }
        },
        RESET(){
            @Override
            public Tuple<Position, Direction> execute(String [] input) {
                currentState = null;
                return currentState;
            }
        };

        public static RobotCommand factory(String input) {

            if(input == null || input.trim().equals("")) {
                return CommandType.REPORT;
            }

            return Stream.of(CommandType.values())
                    .filter(commandType -> commandType.name().equals(input)).findAny()
                    .orElse(CommandType.REPORT);

        }

    }

    //creates the state with Postion and Direction
    private static Function<Tuple<Position, Direction>, Tuple<Position, Direction>> place = tuple -> {
        if(currentState == null){
            currentState = tuple;
        }
        return currentState;
    };

    //alters the state making the resquested moves
    private static BiFunction<Tuple<Position, Direction>, Integer, Tuple<Position, Direction>> move = (tuple, moves) -> {

        Tuple<Position, Direction> newState = null;

        switch (tuple.getB()) {
            case NORTH:
                newState = new Tuple<Position, Direction>(new Position(tuple.getA().getX() , tuple.getA().getY() + 1), tuple.getB());
                break;
            case EAST:
                newState = new Tuple<Position, Direction>(new Position(tuple.getA().getX() + moves, tuple.getA().getY()), tuple.getB());
                break;
            case SOUTH:
                newState = new Tuple<Position, Direction>(new Position(tuple.getA().getX() , tuple.getA().getY() - moves), tuple.getB());
                break;
            case WEST:
                newState = new Tuple<Position, Direction>(new Position(tuple.getA().getX() - moves, tuple.getA().getY() ), tuple.getB());
                break;
            default:
                return tuple;
        }

        if(withinBounds(newState.getA(), bounds)){
            currentState = newState;
        }

        return currentState;

    };

    //alters the state making just one move
    private static Function<Tuple<Position, Direction>, Tuple<Position, Direction>> moveOne = (tuple) -> move.apply(tuple, 1);

    //alters the state rotating to the right
    private static Function<Tuple<Position, Direction>, Tuple<Position, Direction>> rigth = (tuple) -> {

        Tuple<Position, Direction> newState = null;
        Direction[] values = Direction.values();
        for (int i = 0; i < values.length; i++) {
            if (tuple.getB() == values[i]) {
                if (i == values.length - 1) {
                    newState = new Tuple<>(tuple.getA(), values[0]);
                }else{
                    newState = new Tuple<>(tuple.getA(), values[i+1]);
                }
                break;
            }
        }

        currentState = newState;

        return currentState;
    };

    //alters the state rotating to the left
    private static Function<Tuple<Position, Direction>, Tuple<Position, Direction>> left = (tuple) -> {

        Tuple<Position, Direction> newState = null;

        Direction[] values = Direction.values();
        for (int i = 0; i < values.length; i++) {
            if (tuple.getB() == values[i]) {
                if (i == 0) {
                    newState = new Tuple<>(tuple.getA(), values[values.length - 1]);
                }else{
                    newState = new Tuple<>(tuple.getA(), values[i-1]);
                }
                break;
            }
        }


        currentState = newState;

        return currentState;
    };

    public static void main( String[] args ) {

        Scanner scanner = new Scanner(System.in);
        while(true){

            String line = scanner.nextLine();

            if (line == null || line.equals("")){
                continue;
            }

            String[] parts = line.split(" ");

            RobotCommand command = CommandType.factory(parts[0]);

            Tuple<Position, Direction> result = command.execute(parts);

        }

    }

    enum Direction {
        NORTH, EAST, SOUTH, WEST;
        public static Direction getType(String direction) {

            for (Direction d : Direction.values()) {
                if(d.name().equals(direction)){
                    return d;
                }
            };

            return null;
        }

    }

    static class Tuple<A, B> {

        final A a;
        final B b;

        public Tuple(A a, B b) {
            this.a = a;
            this.b = b;
        }

        public A getA() {
            return a;
        }

        public B getB() {
            return b;
        }
    };

    static class Position {

        private int x;
        private int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        @Override
        public String toString() {
            return x + "," + y;
        }
    }

    private static boolean isInt(String s) {
        try {
            Integer.parseInt(s);
            return true;
        }
        catch (NumberFormatException ex){
            return false;
        }
    }


    private static boolean withinBounds(Position position, Position max){

        if (withinBounds(position.getX(), max.getX() ) && withinBounds(position.getY(), max.getY() )){
            return true;
        }

        return false;
    }

    private static boolean withinBounds(int dimension, int limit){
        if (dimension < 0 || dimension > limit -1) {
            return false;
        }

        return true;
    }
}




