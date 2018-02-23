package com._37coins;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com._37coins.bcJsonRpc.BitcoindClientFactory;
import com._37coins.bcJsonRpc.BitcoindInterface;
import com._37coins.bcJsonRpc.pojo.BlockVerbose;

public class Last1000BlocksTest {
	Logger log = LoggerFactory.getLogger(Last1000BlocksTest.class);

	static BitcoindInterface client;

	@Test
	public void test() throws MalformedURLException, IOException {
		BitcoindClientFactory clientFactory = new BitcoindClientFactory(new URL("http://localhost:18332/"), "test",
				"test");
		client = clientFactory.getClient();

		int height = client.getblockcount();
		String blockHash = client.getblockhash(height);
		BlockVerbose block = client.getblock(blockHash, 2);
		for (int i = 0; i < 100; i++) {
			log.info(block.toString());
			block = client.getblock(block.getPreviousblockhash(), 2);
		}
	}

}
