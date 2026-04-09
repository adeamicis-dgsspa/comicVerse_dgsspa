package com.dgsspa.comicverse.service;

import com.dgsspa.comicverse.config.ErrorMessagesProperties;
import com.dgsspa.comicverse.config.SearchMessagesProperties;
import com.dgsspa.comicverse.config.SuccessMessagesProperties;
import com.dgsspa.comicverse.dto.FumettoDTO;
import com.dgsspa.comicverse.dto.SearchResponseDTO;
import com.dgsspa.comicverse.exception.ResourceNotFoundException;
import com.dgsspa.comicverse.mapper.FumettoMapper;
import com.dgsspa.comicverse.model.Fumetto;
import com.dgsspa.comicverse.repository.FumettoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class GenereService {
}
