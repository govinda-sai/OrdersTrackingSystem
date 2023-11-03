package ordersTrackingSystem.rest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import ordersTrackingSystem.entities.Order;
import ordersTrackingSystem.entities.OrderItem;
import ordersTrackingSystem.entities.OrderItemCompositeKey;
import ordersTrackingSystem.entities.Product;
import ordersTrackingSystem.repositories.AllProductDetailsDTO;
import ordersTrackingSystem.repositories.CustomerRepo;
import ordersTrackingSystem.repositories.OrderDTO;
import ordersTrackingSystem.repositories.OrderItemDTO;
import ordersTrackingSystem.repositories.OrderItemDetailsDTO;
import ordersTrackingSystem.repositories.OrderItemRepo;
import ordersTrackingSystem.repositories.OrderRepo;
import ordersTrackingSystem.repositories.ProductRepo;
import ordersTrackingSystem.repositories.ProductsOrderedByCustomerDTO;

@RestController
public class OrdersTrackingSystemController {
	@Autowired
	CustomerRepo customerRepo;

	@Autowired
	ProductRepo productRepo;

	@Autowired
	OrderRepo orderRepo;

	@Autowired
	OrderItemRepo orderItemRepo;

	// 1. 1
//	@Operation(summary = "adding customer", 
//			description = "add a request body to create new customer")
//	@PostMapping("/customers")
//	public Customer addCustomer(@RequestBody Customer customer) {
//		return customerRepo.save(customer);
//	}

	// 1. 2
//	@PutMapping("/customers/{customerId}")
//	@Parameter(description = "customerId of customer that is to be updated")
//	@ApiResponse(responseCode = "200", description = "customer details updated")
//	public Customer updateCustomer(@PathVariable("customerId") Integer customerId, @RequestParam("name") String name) {
//		var optionalCustomer = customerRepo.findById(customerId);
//		if (optionalCustomer.isPresent()) {
//			var customer = optionalCustomer.get();
//			customer.setName(name);
//			customerRepo.save(customer);
//			return customer;
//		} else {
//			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "customer id not found");
//		}
//	}

	// 1. 3
//	@DeleteMapping("/customers/{customerId}")
//	public void deleteCustomer(@PathVariable("customerId") Integer customerId) {
//		try {
//			Optional<Customer> optionalCustomer = customerRepo.findById(customerId);
//			if (optionalCustomer.isPresent()) {
//				Customer customer = optionalCustomer.get();
//				customerRepo.delete(customer);
//			} else {
//				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "customer id not found");
//			}
//		} catch (Exception e) {
//			e.getMessage();
//		}
//	}

	// 2. 1
//	@PostMapping("/products")
//	public Product addProduct(@RequestBody Product product) {
//		return productRepo.save(product);
//	}

	// 2. 2
//	@PutMapping("/products/{productId}")
//	public Product updateProduct(@PathVariable("productId") Integer productId,
//			@RequestParam("name") String name) {
//		var optionalProduct = productRepo.findById(productId);
//		if(optionalProduct.isPresent()) {
//			var product = optionalProduct.get();
//			product.setProductName(name);
//			productRepo.save(product);
//			return product;
//		} else {
//			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "product id not found");
//		}
//	} 

	// 2. 3
//	@DeleteMapping("/products/{productId}")
//	public void deleteProduct(@PathVariable("productId") Integer productId) {
//		Optional<Product> optionalProduct = productRepo.findById(productId);
//		if(optionalProduct.isPresent()) {
//			Product product = optionalProduct.get();
//			productRepo.delete(product);
//		} else {
//			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "product id not found");
//		}
//	}

	// 3. 1
	@PostMapping("/orders")
	public void addOrder(@RequestBody OrderDTO orderDTO) {
		Order order = new Order();
		order.setOrderDate(orderDTO.getOrderDate());
		order.setCustomerId(orderDTO.getCustomerId());
		order.setStatus(orderDTO.getStatus());
		order.setExpectedDeliveryDate(orderDTO.getExpectedDeliveryDate());

		// Save the order to generate the orderId
		Order savedOrder = orderRepo.save(order);

		for (OrderItemDTO orderItemDTO : orderDTO.getOrderItems()) {
			OrderItem orderItem = new OrderItem();
			OrderItemCompositeKey orderItemCK = new OrderItemCompositeKey();

			orderItemCK.setOrderId(savedOrder.getOrderId()); // Set the orderId after saving the order
			orderItemCK.setProductId(orderItemDTO.getProductId());

			orderItem.setOrderItemCK(orderItemCK);

			orderItem.setQuantity(orderItemDTO.getQuantity());
			orderItem.setTotalPrice(orderItemDTO.getTotalPrice());

			/* @ManyToOne */
			orderItem.setOrder(savedOrder);

	        /* @OneToMany */
			savedOrder.getOrderItems().add(orderItem);
		}

		// Save the order items
		orderRepo.save(savedOrder);
	}

	// 3. 2
	@DeleteMapping("/orders/{orderId}")
	public void deleteOrder(@PathVariable("orderId") Integer orderId) {
		Optional<Order> optionalOrder = orderRepo.findById(orderId);
		if (optionalOrder.isPresent()) {
			Order order = optionalOrder.get();
			orderRepo.delete(order);
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "order id not found");
		}
	}

//	@GetMapping("/orders")
//	public List<Order> getOrders() {
//		return orderRepo.findAll();
//	}

	// 4
//	@PutMapping("/orders/{orderId}")
//	public Order updateOrderStatus (@PathVariable("orderId") Integer orderId,
//							@RequestParam("status") Character status) {
//		var optionalOrder = orderRepo.findById(orderId);
//		if (optionalOrder.isPresent()) {
//			var order = optionalOrder.get();
//			order.setStatus(status);
//			orderRepo.save(order);
//			return order;
//		} else {
//			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "order id not found");
//		}
//	}

	// 5
//	@GetMapping("/customers")
//	public List<Customer> getCustomers() {
//		return customerRepo.findAll();
//	}

//	@Operation(description = "get customer details by page, page numbers starts from 0")
//	@Parameter(description = "enter page number")
//	@GetMapping("/customers/{pageno}")
//	public Page<Customer> getCustomerFromPage (@PathVariable("pageno") Integer pageno) {
//		return customerRepo.findAll(PageRequest.of(pageno, 3));
//	}

	// 6
//	@GetMapping("/products")
//	public List<Product> getProducts() {
//		return productRepo.findAll();
//	}

	// 7
//	@GetMapping("/orders/{custId}")
//	public List<Order> getOrdersByCustomerId (@PathVariable("custId") Integer customerId) {
//		return orderRepo.findByCustomerId(customerId);
//	}

	// 8
	@GetMapping("/orders/{status}")
	public List<Order> getOrdersByStatus(@PathVariable("status") Character status) {
		return orderRepo.findAllByStatus(status);
	}

	// 9
//	@GetMapping("/orders")
//	public List<OrderItemsDTO> getOrders() {
//		return orderRepo.getOrderItems();
//	}

	// 10
	@GetMapping("/products/{string}")
	public List<Product> getProductsWithGivenString(@PathVariable("string") String string) {
		return productRepo.findByProductNameContaining(string);
	}

	// 11
	@GetMapping("/products/product-details/{productId}")
	public List<AllProductDetailsDTO> getProductDetails(@PathVariable("productId") Integer productId) {
		var optionalProduct = productRepo.findById(productId);
		if (optionalProduct.isPresent()) {
			return orderItemRepo.getAllProductSaleDetails(productId);
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "product id not found");
		}
	}

	// 12
	@GetMapping("orders/all-order-details/{orderId}")
	public List<OrderItemDetailsDTO> getAllOrderDetails(@PathVariable("orderId") Integer orderId) {
		return orderItemRepo.findByOrderId(orderId);
	}

	// 13
	@GetMapping("/products/products-ordered-by-customer/{customerId}")
	public List<ProductsOrderedByCustomerDTO> getProductsOrderedByCustomer(
			@PathVariable("customerId") Integer customerId) {
		return orderItemRepo.findAllProductsByCustomerId(customerId);
	}

	// 14
	@GetMapping("/orders/between-dates")
	public List<Order> getOrdersBetweenDates(@RequestParam("startDate") String startDate,
			@RequestParam("endDate") String endDate) {
		LocalDate parseStartDate = LocalDate.parse(startDate);
		LocalDate parseEndDate = LocalDate.parse(endDate);

		return orderRepo.getOrdersBetween(parseStartDate, parseEndDate);
	}
}