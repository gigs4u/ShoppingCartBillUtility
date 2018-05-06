package com.checkoutcounter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.checkoutcounter.domain.service.impl.ShoppingCartService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShoppingCartCheckOutApplicationTests {

	@Test
	public void contextLoads() {
	}
	
	@Autowired
	private ShoppingCartService shoppingCartService;
	
	@Test
	public void createNewCartIdTest() {
		
		
		/*EasyMock.expect(orderRepositoryMock.createNewOrder()).andReturn(prepareMockOrderSummary(System.currentTimeMillis(), new Date(), OrderStatus.CREATED));
		EasyMock.replay(orderRepositoryMock);
		
		WebClient client = prepareWebClient();
		client.accept(MediaType.APPLICATION_JSON_TYPE);
		client.path("new");
		
		Long cartId = client.get(Long.class);
		Assert.assertNotNull(cartId);
		
		EasyMock.verify(orderRepositoryMock);*/
	}

}
