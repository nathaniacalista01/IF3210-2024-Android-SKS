package com.example.bondoman.lib

import com.example.bondoman.entities.Transaction
import com.example.bondoman.exceptions.InvalidFileFormat
import org.apache.poi.hssf.usermodel.HSSFWorkbook
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.io.OutputStream

class TransactionExcelAdapter: ITransactionFileAdapter {
    override fun save(transactions: List<Transaction>, fileName: String, outputStream: OutputStream) {
        val workbook =
            if (fileName.endsWith(".xlsx")) {
                XSSFWorkbook()
            } else if (fileName.endsWith(".xls")) {
                HSSFWorkbook()
            } else {
                throw InvalidFileFormat()
            }

        val sheet = workbook.createSheet("Transactions")

        val columnNames = sheet.createRow(0)
        columnNames.createCell(0).setCellValue("id")
        columnNames.createCell(1).setCellValue("title")
        columnNames.createCell(2).setCellValue("category")
        columnNames.createCell(3).setCellValue("amount")
        columnNames.createCell(4).setCellValue("location")


        for ((index, transaction) in transactions.withIndex()) {
            val row = sheet.createRow(index + 1)
            val idCell = row.createCell(0)
            idCell.setCellValue(transaction.id.toDouble())
            val titleCell = row.createCell(1)
            titleCell.setCellValue(transaction.title)
            val categoryCell = row.createCell(2)
            categoryCell.setCellValue(transaction.category)
            val amountCell = row.createCell(3)
            amountCell.setCellValue(transaction.amount.toDouble())
            val locationCell = row.createCell(4)
            locationCell.setCellValue(transaction.location)
        }

//        val fos = FileOutputStream(fileName)
        workbook.write(outputStream)
    }
}