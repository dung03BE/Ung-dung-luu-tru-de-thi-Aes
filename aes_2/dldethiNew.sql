-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 17, 2024 at 09:13 AM
-- Server version: 8.0.36
-- PHP Version: 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `dldethi`
--

-- --------------------------------------------------------

--
-- Table structure for table `account`
--

CREATE TABLE `account` (
  `ID` int NOT NULL,
  `Username` varchar(50) NOT NULL,
  `Password` varchar(255) NOT NULL,
  `MaGiangVien` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `account`
--

INSERT INTO `account` (`ID`, `Username`, `Password`, `MaGiangVien`) VALUES
(1, '1', '1', '20001'),
(2, 'user2', 'password2', '20002'),
(3, 'user3', 'password3', '20003');

-- --------------------------------------------------------

--
-- Table structure for table `answer`
--

CREATE TABLE `answer` (
  `AnswerID` int NOT NULL,
  `Content` text NOT NULL,
  `QuestionID` int DEFAULT NULL,
  `isCorrect` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `answer`
--

INSERT INTO `answer` (`AnswerID`, `Content`, `QuestionID`, `isCorrect`) VALUES
(1, 'Java is a programming language.', 1, 1),
(2, 'Java is a type of coffee.', 1, 0),
(3, 'Data structures are a way to organize data.', 2, 1),
(4, 'Data structures are a type of algorithm.', 2, 0),
(5, 'Linear algebra is a branch of mathematics.', 3, 1),
(6, 'Linear algebra is a type of geometry.', 3, 0),
(8, 'NuCNmGrmzACBZYvcJH0sgg==', 5, 0),
(9, 'swThrp6YjgVdd+QMWBhRzw==', 6, 0),
(10, 'zIVdKhpy5buLaXfc3CymJH6R1a3dpbiI452IHAXzO4A=', 7, 0),
(11, 'WV/ipkR8y/0JwPmx64QaVQ==', 8, 0),
(12, 'yoFAf7bxL53ZbkC1vMu0Hw==', 8, 0),
(13, 'UI07tqWWFtb60hSIgPMPMg==', 10, 1),
(14, 'D10no4BNbMMCWd1agG+Png==', 10, 0),
(15, 'Mag2wAQIHjKn2pxfenxhQg==', 10, 0),
(16, '+lS77mlO4pDWKSiwHq+UVQ==', 11, 0),
(17, 'aRKERhIBBmIoiGHLoLOcIw==', 11, 0),
(18, 'XBzyT+JgpfTHgvy0IipF5A==', 12, 0);

-- --------------------------------------------------------

--
-- Table structure for table `exam`
--

CREATE TABLE `exam` (
  `ExamID` int NOT NULL,
  `Code` varchar(20) NOT NULL,
  `Title` varchar(100) DEFAULT NULL,
  `Duration` int DEFAULT NULL,
  `CreateDate` date DEFAULT NULL,
  `MaGiangVien` varchar(10) DEFAULT NULL,
  `MaMonHoc` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `exam`
--

INSERT INTO `exam` (`ExamID`, `Code`, `Title`, `Duration`, `CreateDate`, `MaGiangVien`, `MaMonHoc`) VALUES
(1, 'EX001', 'Midterm Exam Java', 90, '2024-04-01', '20001', 'IT6001'),
(2, 'EX002', 'Final Exam Data Structures', 120, '2024-05-01', '20002', 'IT6002'),
(3, 'EX003', 'Midterm Exam Algebra', 60, '2024-06-01', '20003', 'MATH101'),
(6, 'A3', 'Đề thi 3', 90, '2024-06-15', '20002', 'IT6001'),
(7, 'aaaaa', 'aaa', 34, '2024-06-15', '20002', 'IT6001'),
(8, 'a8', 'dsdsd', 90, '2024-06-16', '20001', 'IT6001');

-- --------------------------------------------------------

--
-- Table structure for table `examquestion`
--

CREATE TABLE `examquestion` (
  `ExamID` int NOT NULL,
  `QuestionID` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `examquestion`
--

INSERT INTO `examquestion` (`ExamID`, `QuestionID`) VALUES
(1, 1),
(1, 2),
(2, 2),
(2, 3),
(3, 3),
(1, 5),
(6, 6),
(6, 7),
(7, 8),
(7, 9),
(6, 10),
(6, 11),
(8, 12);

-- --------------------------------------------------------

--
-- Table structure for table `giangvien`
--

CREATE TABLE `giangvien` (
  `MaGiangVien` varchar(10) NOT NULL,
  `TenGiangVien` varchar(100) DEFAULT NULL,
  `SDT` varchar(15) DEFAULT NULL,
  `Khoa` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `giangvien`
--

INSERT INTO `giangvien` (`MaGiangVien`, `TenGiangVien`, `SDT`, `Khoa`) VALUES
('20001', 'Nguyen Van A', '0123456789', 'CNTT'),
('20002', 'Tran Thi B', '0987654321', 'Toan'),
('20003', 'Le Van C', '0912345678', 'Ly');

-- --------------------------------------------------------

--
-- Table structure for table `giangvienmonhoc`
--

CREATE TABLE `giangvienmonhoc` (
  `MaGiangVien` varchar(10) NOT NULL,
  `MaMonHoc` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `giangvienmonhoc`
--

INSERT INTO `giangvienmonhoc` (`MaGiangVien`, `MaMonHoc`) VALUES
('20001', 'IT6001'),
('20003', 'IT6001'),
('20001', 'IT6002'),
('20002', 'MATH101');

-- --------------------------------------------------------

--
-- Table structure for table `monhoc`
--

CREATE TABLE `monhoc` (
  `MaMonHoc` varchar(10) NOT NULL,
  `TenMonHoc` varchar(100) DEFAULT NULL,
  `SoTin` int DEFAULT NULL,
  `Khoa` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `monhoc`
--

INSERT INTO `monhoc` (`MaMonHoc`, `TenMonHoc`, `SoTin`, `Khoa`) VALUES
('IT6001', 'Lap trinh Java', 4, 'CNTT'),
('IT6002', 'Cau truc du lieu', 3, 'CNTT'),
('MATH101', 'Dai so tuyen tinh', 3, 'Toan');

-- --------------------------------------------------------

--
-- Table structure for table `question`
--

CREATE TABLE `question` (
  `QuestionID` int NOT NULL,
  `Content` text NOT NULL,
  `MaGiangVien` varchar(30) DEFAULT NULL,
  `CreateDate` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `question`
--

INSERT INTO `question` (`QuestionID`, `Content`, `MaGiangVien`, `CreateDate`) VALUES
(1, 'What is Java?', '20001', '2024-01-01'),
(2, 'Explain the concept of data structures.', '20002', '2024-02-01'),
(3, 'What is linear algebra?', '20003', '2024-03-01'),
(5, 'hu4eD/G1vaVZW7BNdozC6A==', '20001', '2024-06-15'),
(6, 'cI2YwZ0HSNGmO40+T5oung==', '20002', '2024-06-15'),
(7, 's/KQJSwsz7xew3xscikIfnuHMX5EW44iqzeR56pREYA=', '20001', '2024-06-15'),
(8, 'YKtrqsu1MBBRQmJ4IFascA==', '20002', '2024-06-15'),
(9, 'TwZbUxfWC4fOdnMqWClIU3z4cD05RbfqHMTUYbDUH/Y=', '20002', '2024-06-15'),
(10, 'iQNRK5WhFEegKTjqe6EtyQ==', '20002', '2024-06-15'),
(11, 'UsdxWaSRC/Ou4RK21uG+4A==', '20002', '2024-06-15'),
(12, 'z50JZ2Xi7TnvHT3wai1nAhuy7y7o4l5VzUTSucq07no=', '20001', '2024-06-16');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `account`
--
ALTER TABLE `account`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `Username` (`Username`),
  ADD KEY `MaGiangVien` (`MaGiangVien`);

--
-- Indexes for table `answer`
--
ALTER TABLE `answer`
  ADD PRIMARY KEY (`AnswerID`),
  ADD KEY `QuestionID` (`QuestionID`);

--
-- Indexes for table `exam`
--
ALTER TABLE `exam`
  ADD PRIMARY KEY (`ExamID`),
  ADD UNIQUE KEY `Code` (`Code`),
  ADD KEY `MaGiangVien` (`MaGiangVien`),
  ADD KEY `MaMonHoc` (`MaMonHoc`);

--
-- Indexes for table `examquestion`
--
ALTER TABLE `examquestion`
  ADD PRIMARY KEY (`ExamID`,`QuestionID`),
  ADD KEY `QuestionID` (`QuestionID`);

--
-- Indexes for table `giangvien`
--
ALTER TABLE `giangvien`
  ADD PRIMARY KEY (`MaGiangVien`);

--
-- Indexes for table `giangvienmonhoc`
--
ALTER TABLE `giangvienmonhoc`
  ADD PRIMARY KEY (`MaGiangVien`,`MaMonHoc`),
  ADD KEY `MaMonHoc` (`MaMonHoc`);

--
-- Indexes for table `monhoc`
--
ALTER TABLE `monhoc`
  ADD PRIMARY KEY (`MaMonHoc`);

--
-- Indexes for table `question`
--
ALTER TABLE `question`
  ADD PRIMARY KEY (`QuestionID`),
  ADD KEY `MaGiangVien` (`MaGiangVien`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `account`
--
ALTER TABLE `account`
  MODIFY `ID` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `answer`
--
ALTER TABLE `answer`
  MODIFY `AnswerID` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT for table `exam`
--
ALTER TABLE `exam`
  MODIFY `ExamID` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `question`
--
ALTER TABLE `question`
  MODIFY `QuestionID` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `account`
--
ALTER TABLE `account`
  ADD CONSTRAINT `account_ibfk_1` FOREIGN KEY (`MaGiangVien`) REFERENCES `giangvien` (`MaGiangVien`) ON DELETE SET NULL ON UPDATE CASCADE;

--
-- Constraints for table `answer`
--
ALTER TABLE `answer`
  ADD CONSTRAINT `answer_ibfk_1` FOREIGN KEY (`QuestionID`) REFERENCES `question` (`QuestionID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `exam`
--
ALTER TABLE `exam`
  ADD CONSTRAINT `exam_ibfk_1` FOREIGN KEY (`MaGiangVien`) REFERENCES `giangvien` (`MaGiangVien`) ON DELETE SET NULL ON UPDATE CASCADE,
  ADD CONSTRAINT `exam_ibfk_2` FOREIGN KEY (`MaMonHoc`) REFERENCES `monhoc` (`MaMonHoc`) ON DELETE SET NULL ON UPDATE CASCADE;

--
-- Constraints for table `examquestion`
--
ALTER TABLE `examquestion`
  ADD CONSTRAINT `examquestion_ibfk_1` FOREIGN KEY (`ExamID`) REFERENCES `exam` (`ExamID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `examquestion_ibfk_2` FOREIGN KEY (`QuestionID`) REFERENCES `question` (`QuestionID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `giangvienmonhoc`
--
ALTER TABLE `giangvienmonhoc`
  ADD CONSTRAINT `giangvienmonhoc_ibfk_1` FOREIGN KEY (`MaGiangVien`) REFERENCES `giangvien` (`MaGiangVien`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `giangvienmonhoc_ibfk_2` FOREIGN KEY (`MaMonHoc`) REFERENCES `monhoc` (`MaMonHoc`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `question`
--
ALTER TABLE `question`
  ADD CONSTRAINT `question_ibfk_1` FOREIGN KEY (`MaGiangVien`) REFERENCES `giangvien` (`MaGiangVien`) ON DELETE SET NULL ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
