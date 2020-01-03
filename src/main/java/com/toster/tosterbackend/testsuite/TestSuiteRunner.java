package com.toster.tosterbackend.testsuite;

import io.vavr.control.Try;
import org.springframework.stereotype.Component;


@Component
public class TestSuiteRunner {

    public void runTestSuite() {

        Try.run(() ->
        {
            Process proc = Runtime.getRuntime()
                    .exec("/home/msasin/www/tosterApp/run.sh");

            /*BufferedReader stdInput = new BufferedReader(new
                    InputStreamReader(proc.getInputStream()));

            stdInput.lines().collect(Collectors.toList()).forEach(System.out::println);*/
        }).onFailure(Throwable::printStackTrace);
    }


}
