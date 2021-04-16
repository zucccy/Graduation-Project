package cn.edu.zucc.controller;

import cn.edu.zucc.OfficeService;
import cn.edu.zucc.commonVO.ResponsePageVO;
import cn.edu.zucc.commonVO.ResponseVO;
import cn.edu.zucc.exception.FormException;
import cn.edu.zucc.po.Office;
import cn.edu.zucc.utils.PageUtils;
import cn.edu.zucc.utils.ResponseBuilder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Description: TODO
 *
 * @author chenyun
 * @since 15.04.2021
 */
@Slf4j
@RestController
@Api(value = "科室模块", tags = "科室信息接口")
@RequestMapping("/office")
public class OfficeController {

    @Resource
    private OfficeService officeService;

    @ApiOperation(value = "根据科室id查找科室信息")
    @GetMapping("{id}")
    public ResponseVO<Office> findOfficeById(@PathVariable Long id) {

        if (null == id) {
            throw new FormException();
        }
        return ResponseBuilder.success(officeService.findOfficeById(id));
    }

    @ApiOperation(value = "获取所有科室名称")
    @GetMapping("/getOfficeNameAll")
    public ResponseVO<List<String>> getOfficeNameAll() {
        return ResponseBuilder.success(officeService.findOfficeListAll()
                .stream().map(Office::getOfficeName).collect(Collectors.toList()));
    }

    @ApiOperation(value = "根据科室编号获取所有子科室名称")
    @GetMapping("/getChildOfficeName/{id}")
    public ResponseVO<List<String>> getChildOfficeName(@PathVariable Long id) {

        if (null == id) {
            throw new FormException();
        }
        return ResponseBuilder.success(officeService.findOfficeList(id)
                .stream().map(Office::getOfficeName).collect(Collectors.toList()));
    }

    @ApiOperation(value = "根据医院编号查找医院中所有科室信息")
    @GetMapping("/getOfficeListByHosId/{hospitalId}")
    public ResponsePageVO<Office> getOfficeListByHosId(@PathVariable Long hospitalId,
                                                       @RequestParam(defaultValue = "1") Integer pageNum,
                                                       @RequestParam(defaultValue = "10") Integer pageSize) {
        if (null == hospitalId) {
            throw new FormException();
        }
        return ResponseBuilder.successPageable(PageUtils.restPage(officeService.findOfficeListByHosId(hospitalId, pageNum, pageSize)));
    }

}
