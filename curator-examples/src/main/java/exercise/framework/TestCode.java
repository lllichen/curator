package exercise.framework;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by lichen@daojia.com on 2018-5-28.
 */
public class TestCode {


    public static void main(String[] args) throws IOException {
        while (true){
            String value = new BufferedReader(new InputStreamReader(System.in)).readLine();

            System.out.println(value);
            if(value.equals( "exit" ))
                break;
        }


    }
}
