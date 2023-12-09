package com.example.iot_backend.service.impl;

import com.example.iot_backend.entity.Bill;
import com.example.iot_backend.entity.BillDetails;
import com.example.iot_backend.entity.Product;
import com.example.iot_backend.repository.BillDetailRepository;
import com.example.iot_backend.repository.BillRepository;
import com.example.iot_backend.repository.ProductRepository;
import com.example.iot_backend.service.BillDetailService;
import com.example.iot_backend.service.BillService;
import com.example.iot_backend.utils.mapper;
import com.example.iot_backend.utils.response.BillDetailCustomResponse;
import com.example.iot_backend.utils.response.BillDetailResponse;
import com.example.iot_backend.utils.response.BillResponse;
import com.example.iot_backend.utils.response.UserDetailResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.ObjectUtils;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class BillDetailServiceImpl implements BillDetailService {
    private final BillDetailRepository billDetailRepository;
    private final BillService billService;
    private final ProductRepository productRepository;
    private final BillRepository billRepository;
    @Override
    public List<BillDetailResponse> getAllBillDetailByIdBill(Long id_bill) {
        return mapper.billDetailResponseList(StreamSupport.stream(billDetailRepository.findBillDetailsByBillId(id_bill).spliterator(),false).collect(Collectors.toList()));
    }
    @Override
    public List<BillDetailCustomResponse> getAllproductSoldlByDate(String date1, String date2) {
        List<BillResponse> billResponses = billService.findByDate(date1, date2);
        List<BillDetailResponse> billDetailResponseList = new ArrayList<>();
        List<BillDetailCustomResponse> billDetailCustomResponses = new ArrayList<>();
        Map<String, Long> mapProduct = new HashMap<>();
        for(BillResponse billRespons : billResponses){
            List<BillDetailResponse> result = getAllBillDetailByIdBill(billRespons.getId());
            for(BillDetailResponse i : result){
                billDetailResponseList.add(i);
                if(mapProduct.isEmpty()){
                    mapProduct.put(i.getProduct_name(),i.getQuantity_sold());
                }else if(mapProduct.containsKey(i.getProduct_name())){
                    Long currentQuantity = mapProduct.get(i.getProduct_name());
                    currentQuantity += i.getQuantity_sold();
                    mapProduct.put(i.getProduct_name(), currentQuantity);
                }else if(!mapProduct.containsKey(i.getProduct_name())){
                    mapProduct.put(i.getProduct_name(), i.getQuantity_sold());
                }
            }
        }
        for (Map.Entry<String, Long> entry : mapProduct.entrySet()){
            BillDetailCustomResponse customResponse = new BillDetailCustomResponse();
            Product product = productRepository.findProductByName(entry.getKey());
            customResponse.setProduct_name(entry.getKey());
            customResponse.setQuantity_sold(entry.getValue());
            customResponse.setId_product(product.getId());
            customResponse.setQuantity_remain(product.getQuantity());
            customResponse.setPrice_sold(product.getPrice() * Double.valueOf(entry.getValue()));
            billDetailCustomResponses.add(customResponse);
        }
        return billDetailCustomResponses;
    }
    @Override
    public List<BillDetailCustomResponse> getProductSoldByName(String name) {
        List<BillResponse> billResponses = billService.getAllBill();
        List<BillDetailResponse> billDetailResponseList = new ArrayList<>();
        List<BillDetailCustomResponse> billDetailCustomResponses = new ArrayList<>();

        Map<String, Long> mapProduct = new HashMap<>();
        for(BillResponse billRespons : billResponses){
            List<BillDetailResponse> result = getAllBillDetailByIdBill(billRespons.getId());
            for(BillDetailResponse i : result){
                billDetailResponseList.add(i);
                if(mapProduct.isEmpty()){
                    mapProduct.put(i.getProduct_name(),i.getQuantity_sold());
                }else if(mapProduct.containsKey(i.getProduct_name())){
                    Long currentQuantity = mapProduct.get(i.getProduct_name());
                    currentQuantity += i.getQuantity_sold();
                    mapProduct.put(i.getProduct_name(), currentQuantity);
                }else if(!mapProduct.containsKey(i.getProduct_name())){
                    mapProduct.put(i.getProduct_name(), i.getQuantity_sold());
                }
            }
        }
        String normalizedInput = StringUtils.stripAccents(name.toLowerCase().trim().replaceAll("\\s+", " "));
        for (Map.Entry<String, Long> entry : mapProduct.entrySet()){
            if(StringUtils.stripAccents(entry.getKey().toLowerCase()).contains(normalizedInput)){
                BillDetailCustomResponse customResponse = new BillDetailCustomResponse();
                Product product = productRepository.findProductByName(entry.getKey());
                customResponse.setProduct_name(entry.getKey());
                customResponse.setQuantity_sold(entry.getValue());
                customResponse.setId_product(product.getId());
                customResponse.setQuantity_remain(product.getQuantity());
                customResponse.setPrice_sold(product.getPrice() * Double.valueOf(entry.getValue()));
                billDetailCustomResponses.add(customResponse);
            }
        }
        return billDetailCustomResponses;
    }
    @Override
    public BillDetailCustomResponse getProductSoldById(Long id) {
        List<BillDetails> billDetails = billDetailRepository.findBillDetailsByProductId(id);

        Double priceSold = Double.valueOf(0) ;
        Long quantitySold = Long.valueOf(0);
        for(BillDetails b : billDetails){
            priceSold += b.getPrice_sold();
            quantitySold += b.getQuantity_sold();
        }
        Product product = productRepository.findProductById(id);
        BillDetailCustomResponse billDetailCustomResponse = new BillDetailCustomResponse();
        billDetailCustomResponse.setPrice_sold(priceSold);
        billDetailCustomResponse.setQuantity_sold(quantitySold);
        billDetailCustomResponse.setProduct_name(product.getName());
        billDetailCustomResponse.setQuantity_remain(product.getQuantity());
        billDetailCustomResponse.setId_product(product.getId());
        return billDetailCustomResponse;
    }

    @Override
    public List<UserDetailResponse> getUserSoldProductByIdProduct(Long id) {
        List<UserDetailResponse> result = new ArrayList<>();
        List<BillDetails> billDetailsList = billDetailRepository.findBillDetailsByProductId(id);
        Set<Long> userId = new HashSet<>();
        for(BillDetails b : billDetailsList){

//            if(userId.isEmpty()){
//                userId.add(bill.getUser().getId());
//
//            }else if(!userId.contains(bill.getUser().getId())){
//
//            }
            Bill bill = billRepository.findBillById(b.getBill().getId());
            if(!ObjectUtils.isEmpty(bill.getUser())){
                UserDetailResponse userDetailResponse = new UserDetailResponse();
                userDetailResponse.setId_user(bill.getUser().getId());
                userDetailResponse.setName(bill.getUser().getName());
                userDetailResponse.setQuantity_product(b.getQuantity_sold());
                userDetailResponse.setSale_date(bill.getCreated());
                userDetailResponse.setId_bill(bill.getId());
                result.add(userDetailResponse);
            }
        }
        return result;
    }

    @Override
    public List<BillDetailResponse> getDetailBillOfUserByIdBill(Long idbill) {
        return mapper.billDetailResponseList(billDetailRepository.findBillDetailsByBillId(idbill).stream().collect(Collectors.toList()));
    }

    @Override
    public BillDetailCustomResponse getProductByIdInRange(String date1, String date2, Long id) {
        List<BillDetailCustomResponse> billDetailCustomResponseList = getAllproductSoldlByDate(date1,date2);
        BillDetailCustomResponse billDetailCustomResponse = new BillDetailCustomResponse();
        for(BillDetailCustomResponse b : billDetailCustomResponseList){
            if(b.getId_product() == id){
                billDetailCustomResponse = b;
                break;
            }
        }
        return billDetailCustomResponse;
    }

    @Override
    public List<BillDetailCustomResponse> getProductsByNameInRange(String date1, String date2, String name) {
        List<BillDetailCustomResponse> billDetailCustomResponseList = getAllproductSoldlByDate(date1,date2);
        List<BillDetailCustomResponse> result = new ArrayList<>();
        String normalizedInput = StringUtils.stripAccents(name.toLowerCase().trim().replaceAll("\\s+", " "));
        for(BillDetailCustomResponse b :billDetailCustomResponseList){
            String normalizedproductName = StringUtils.stripAccents(b.getProduct_name().toLowerCase());
            if(normalizedproductName.contains(normalizedInput)){
                result.add(b);
            }
        }
        return result;
    }





}
