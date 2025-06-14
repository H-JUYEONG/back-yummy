package com.javaex.order.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.common.storage.S3Service;
import com.javaex.order.model.HyunOrderDetailVo;
import com.javaex.order.model.HyunOrderVo;
import com.javaex.order.repository.HyunOrderDao;

@Service
public class HyunOrderService {

	@Autowired
	private HyunOrderDao hyunOrderDao;

	@Autowired
	private S3Service s3Service; // S3Service 주입

	public List<HyunOrderVo> getOrdersByVenderId(int venderId) {
		return hyunOrderDao.getOrdersByVenderId(venderId);
	}

	public HyunOrderDetailVo getOrderDetails(int orderId) {
		return hyunOrderDao.getOrderDetailsById(orderId);
	}

	public void updateOrderStatus(int orderId, String orderStatus) {
		if (orderStatus == null || orderId <= 0) {
			throw new IllegalArgumentException("Invalid orderId or orderStatus");
		}
		hyunOrderDao.updateOrderStatus(orderId, orderStatus);
	}

	public void updateOrderMedia(int orderId, MultipartFile videoFile, MultipartFile photoFile) {
		if (orderId <= 0) {
			throw new IllegalArgumentException("Invalid orderId");
		}

		try {
			// 파일 업로드 처리
			String videoUrl = null;
			String photoUrl = null;

			if (videoFile != null && !videoFile.isEmpty()) {
				videoUrl = s3Service.uploadFile(videoFile);
			}

			if (photoFile != null && !photoFile.isEmpty()) {
				photoUrl = s3Service.uploadFile(photoFile);
			}

			// 업로드된 파일이 없을 경우에도 null 값을 업데이트하도록 처리
			if (videoUrl != null || photoUrl != null) {
				hyunOrderDao.updateOrderMedia(orderId, videoUrl, photoUrl);
			} else {
				throw new IllegalArgumentException("업로드할 파일이 없습니다.");
			}
		} catch (IOException e) {
			throw new RuntimeException("파일 업로드 실패", e);
		}
	}

}
