package spring.boot.example

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpStatus
import spock.lang.Specification

/**
 *
 * @author o.nakrayniko on 05.03.2018.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApplicationControllerTest extends Specification {
    @Autowired
    TestRestTemplate http;

    def "test index"() {
        when:
        def e = http.getForEntity("/", String.class)
        then:
        e.statusCode == HttpStatus.OK
        e.body == 'Hello World! App file: unknown'
    }
}
