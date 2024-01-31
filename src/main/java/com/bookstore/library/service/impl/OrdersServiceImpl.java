package com.bookstore.library.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bookstore.library.entity.Book;
import com.bookstore.library.entity.Customer;
import com.bookstore.library.entity.Orders;
import com.bookstore.library.entity.OrdersList;
import com.bookstore.library.entity.dto.CustomerDTO;
import com.bookstore.library.entity.dto.OrdersDTO;
import com.bookstore.library.entity.dto.OrdersListDTO;
import com.bookstore.library.entity.dto.OrdersWithCustomerDTO;
import com.bookstore.library.repository.BookRepository;
import com.bookstore.library.repository.CustomerRepository;
import com.bookstore.library.repository.OrdersListRepository;
import com.bookstore.library.repository.OrdersRepository;
import com.bookstore.library.service.OrdersService;

import jakarta.validation.constraints.NotNull;

@Service
@Transactional
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    private final OrdersRepository ordersRepository;
    private final OrdersListRepository ordersListRepository;
    private final CustomerRepository customerRepository;
    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;

    public OrdersServiceImpl(OrdersRepository ordersRepository,
            OrdersListRepository ordersListRepository,
            CustomerRepository customerRepository,
            BookRepository bookRepository,
            ModelMapper modelMapper) {
        this.ordersRepository = ordersRepository;
        this.ordersListRepository = ordersListRepository;
        this.customerRepository = customerRepository;
        this.bookRepository = bookRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional(readOnly= true)
    public List<OrdersWithCustomerDTO> findAll() {
        List<Orders> orders = ordersRepository.findAll();
/*
//-------- Test
        List<OrdersList> _orders = new ArrayList<OrdersList>();
        for(Orders _order: orders) {
//            _orders.addAll(_order.getOrders());
            List<OrdersList> ol = _order.getOrders();
            for (OrdersList _o : ol) {
                System.out.println(_o.getBook().getTitle());
            }
        }
*/
        return orders.stream()
                .map(orderOp -> modelMapper.map(orderOp, OrdersWithCustomerDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly= true)
    public Optional<OrdersWithCustomerDTO> get(@NotNull Long id) {
        Optional<Orders> order = ordersRepository.findById(id);

        //---- Test
        if (order.isPresent()) {
            List<OrdersList> _ol = order.get().getOrders();
            for (OrdersList _o : _ol) {
                System.out.println(_o.getBook().getTitle());
            }
        }

        return (order.isPresent()) ?
            order.map(orderOp -> modelMapper.map(orderOp, OrdersWithCustomerDTO.class)) :
            Optional.empty();
    }

    @Override
    public OrdersWithCustomerDTO create(@NotNull OrdersDTO orderDTO) {
        Orders order = modelMapper.map(orderDTO, Orders.class);
        Orders orderNew = ordersRepository.save(order);
        return modelMapper.map(orderNew, OrdersWithCustomerDTO.class);
    }

    @Override
    public OrdersWithCustomerDTO update(@NotNull OrdersDTO orderDTO) {
        Orders order = modelMapper.map(orderDTO, Orders.class);
        Optional<Orders> customerOrder = ordersRepository.findById(orderDTO.getId());
        if (!customerOrder.isPresent()) {
            return null;
        }
        order.setOrders(customerOrder.get().getOrders());
        order = ordersRepository.save(order);
        return modelMapper.map(order, OrdersWithCustomerDTO.class);
    }

    @Override
    public Optional<OrdersWithCustomerDTO> delete(@NotNull Long id) {
        Optional<Orders> order =  ordersRepository.findById(id);
        if (!order.isPresent()) {
            return Optional.empty();
        }
        ordersRepository.deleteById(id);
        return order.map(orderOp -> modelMapper.map(order, OrdersWithCustomerDTO.class));
    }

//--------------------------------------------------

    @Override
    public OrdersWithCustomerDTO updateCustomer(@NotNull Long id, @NotNull OrdersDTO orderDTO) {
        Optional<Orders> order = ordersRepository.findById(orderDTO.getId());
        if (!order.isPresent())
            return null;
        Optional<Customer> customer = customerRepository.findById(id);
        if (!customer.isPresent()) {
            return null;
        }
        OrdersWithCustomerDTO oOrderDTO= modelMapper.map(order, OrdersWithCustomerDTO.class);
        CustomerDTO oCustomerDTO = modelMapper.map(customer, CustomerDTO.class);
        oOrderDTO.setCustomer(oCustomerDTO);
        Orders orderNew = ordersRepository.save(modelMapper.map(oOrderDTO, Orders.class));
        return modelMapper.map(orderNew, OrdersWithCustomerDTO.class);
    }

    @Override
    public OrdersWithCustomerDTO addBook(@NotNull Long id, @NotNull OrdersDTO orderDTO) {
        Optional<Orders> order = ordersRepository.findById(orderDTO.getId());
        if (!order.isPresent())
            return null;
        Optional<Book> book = bookRepository.findById(id);
        if (!book.isPresent()) {
            return null;
        }
        OrdersDTO ordersDTO = modelMapper.map(order, OrdersDTO.class);
        OrdersList ordersList = new OrdersList();
        ordersList.setBook(book.get());
        ordersList.setOrder(order.get());
        ordersList = ordersListRepository.save(ordersList);
        System.out.println(ordersList.toString());
        OrdersListDTO orderListDTO = modelMapper.map(ordersList, OrdersListDTO.class);
        ordersDTO.addOrders(orderListDTO);
        Orders orderNew = ordersRepository.save(modelMapper.map(ordersDTO, Orders.class));
        return modelMapper.map(orderNew, OrdersWithCustomerDTO.class);
    }
}
