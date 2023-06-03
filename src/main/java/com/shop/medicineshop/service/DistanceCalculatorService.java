package com.shop.medicineshop.service;

import com.google.maps.DistanceMatrixApi;
import com.google.maps.DistanceMatrixApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.DistanceMatrixRow;
import com.google.maps.model.TravelMode;

import org.springframework.stereotype.Service;

@Service
public class DistanceCalculatorService {

    private GeoApiContext geoApiContext;

    public DistanceCalculatorService() {
        // Khởi tạo GeoApiContext với khóa API của Google Maps
        geoApiContext = new GeoApiContext.Builder()
                .apiKey("AIzaSyDZtd3-YbY_kWrIfgkMPEz4gM6jiDBtS54")
                .build();
    }

    public double calculateDistance(String origin, String destination) {
        try {
            // Gửi yêu cầu tới API để lấy thông tin khoảng cách
            DistanceMatrixApiRequest request = DistanceMatrixApi.newRequest(geoApiContext);
            DistanceMatrix matrix = request.origins(origin).destinations(destination)
                    .mode(TravelMode.DRIVING)
                    .await();

            // Lấy thông tin khoảng cách từ kết quả trả về
            DistanceMatrixRow row = matrix.rows[0];
            return row.elements[0].distance.inMeters / 1000.0; // Đơn vị: kilômét

        } catch (Exception e) {
            e.printStackTrace();
            return -1; // Trả về -1 nếu xảy ra lỗi
        }
    }

}
