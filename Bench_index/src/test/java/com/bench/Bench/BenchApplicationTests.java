package com.bench.Bench;


import javax.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import com.bench.Bench.util.MD5;
@SpringBootTest
class BenchApplicationTests {
	@Resource
	MD5 d5;

	@Test
	void contextLoads() {
		/* System.out.println(MD5.Md5("ZJ132344")); */
	}

}
