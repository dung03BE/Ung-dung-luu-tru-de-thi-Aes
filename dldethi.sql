-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 10, 2024 at 11:41 AM
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
  `Password` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `account`
--

INSERT INTO `account` (`ID`, `Username`, `Password`) VALUES
(1, '123', '123'),
(2, 'user2', 'password2');

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
(1, 's02KrKLd/XoIdfzU8qaTTnz4cD05RbfqHMTUYbDUH/Y=', 3, 0),
(2, 's02KrKLd/XoIdfzU8qaTTswKeRdzwihT3j65YHdD7+4=', 3, 0),
(5, 'cmMm8IOn+Et2tl3VC5wBYQ==', 6, 1),
(7, '+kkV5pns6P/GWRBRIePu+Q==', 8, 1),
(8, 'wMq1vmJmFBS2Tu5bMC79DA==', 8, 0),
(9, 'EI2NQALeoCw9DTeFYoVwFQ==', 8, 0),
(10, 'nRBTQUGk2kv+qNnRZDnl/A==', 8, 0),
(11, 'DnmFfO2WIjN2sABMYFfmMA==', 9, 1),
(12, 'I21xEqwoMkDevJRtHCDRvw==', 9, 0),
(13, 'qshvDhFumWrGymlcqUFOgw==', 10, 1),
(14, '+OwQLMIOkBOBVajdwTWCww==', 10, 0),
(15, 'qV7tJpVXh1QhGI8crS9Vrg==', 11, 1),
(16, 'hdmJ4Jdx23J7xbv5LTafsg==', 11, 0),
(17, 'Pn4fVJn7Q8wSQqCYwYtMOg==', 12, 1),
(18, '+GZqHbD2fRtNOUNulftKQg==', 12, 0);

-- --------------------------------------------------------

--
-- Table structure for table `categoryquestion`
--

CREATE TABLE `categoryquestion` (
  `CategoryID` int NOT NULL,
  `CategoryName` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `categoryquestion`
--

INSERT INTO `categoryquestion` (`CategoryID`, `CategoryName`) VALUES
(1, 'Java'),
(2, '.NET'),
(3, 'SQL');

-- --------------------------------------------------------

--
-- Table structure for table `exam`
--

CREATE TABLE `exam` (
  `ExamID` int NOT NULL,
  `Code` varchar(50) NOT NULL,
  `Title` varchar(100) NOT NULL,
  `CategoryID` int DEFAULT NULL,
  `Duration` int DEFAULT NULL,
  `CreatorID` int DEFAULT NULL,
  `CreateDate` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `exam`
--

INSERT INTO `exam` (`ExamID`, `Code`, `Title`, `CategoryID`, `Duration`, `CreatorID`, `CreateDate`) VALUES
(3, 'A1', 'dsa', NULL, 90, 2, '2024-06-08 18:26:04'),
(6, 'A6', 'saddsa', NULL, 34, 2, '2024-06-08 18:41:43'),
(8, 'A2', 'English', NULL, 120, 1, '2024-06-08 21:26:28');

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
(3, 4),
(6, 6),
(8, 8),
(8, 9),
(8, 10),
(8, 11),
(8, 12);

-- --------------------------------------------------------

--
-- Table structure for table `question`
--

CREATE TABLE `question` (
  `QuestionID` int NOT NULL,
  `Content` text NOT NULL,
  `CategoryID` int DEFAULT NULL,
  `TypeID` int DEFAULT NULL,
  `CreatorID` int DEFAULT NULL,
  `CreateDate` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `question`
--

INSERT INTO `question` (`QuestionID`, `Content`, `CategoryID`, `TypeID`, `CreatorID`, `CreateDate`) VALUES
(3, '4psYqOvLk1ZGLdtKoVOOWQ==', NULL, NULL, 2, '2024-06-08 18:21:42'),
(4, 'RySDAi90Ep9dls5Mc8jRyw==', NULL, NULL, 2, '2024-06-08 18:26:04'),
(6, 'WMz8Z9ucM05hkRZG2q/1Yw==', NULL, NULL, 2, '2024-06-08 18:41:43'),
(8, 'Cx/5ccrz2YAPKygcWY/+gg==', NULL, NULL, 1, '2024-06-08 21:26:28'),
(9, 'ljO5sqkUEcuLG1+GdGi4c7Ig3zcuXqCe5clRkrEPkZg=', NULL, NULL, 1, '2024-06-08 21:34:52'),
(10, 'AUHYodY+Cu1aAFscumxXRq585qhMUT4xDDz8oL0IeNs=', NULL, NULL, 1, '2024-06-08 21:35:42'),
(11, 'SIAgtJ8JsgPckzF/YxWJ4w==', NULL, NULL, 1, '2024-06-08 21:37:31'),
(12, 'XeWqNXgcr9nKn/OyRLED3g==', NULL, NULL, 1, '2024-06-08 21:37:31');

-- --------------------------------------------------------

--
-- Table structure for table `typequestion`
--

CREATE TABLE `typequestion` (
  `TypeID` int NOT NULL,
  `TypeName` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `typequestion`
--

INSERT INTO `typequestion` (`TypeID`, `TypeName`) VALUES
(1, 'Essay'),
(2, 'Multiple-Choice');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `account`
--
ALTER TABLE `account`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `Username` (`Username`);

--
-- Indexes for table `answer`
--
ALTER TABLE `answer`
  ADD PRIMARY KEY (`AnswerID`),
  ADD KEY `QuestionID` (`QuestionID`);

--
-- Indexes for table `categoryquestion`
--
ALTER TABLE `categoryquestion`
  ADD PRIMARY KEY (`CategoryID`);

--
-- Indexes for table `exam`
--
ALTER TABLE `exam`
  ADD PRIMARY KEY (`ExamID`),
  ADD KEY `CategoryID` (`CategoryID`),
  ADD KEY `CreatorID` (`CreatorID`);

--
-- Indexes for table `examquestion`
--
ALTER TABLE `examquestion`
  ADD PRIMARY KEY (`ExamID`,`QuestionID`),
  ADD KEY `QuestionID` (`QuestionID`);

--
-- Indexes for table `question`
--
ALTER TABLE `question`
  ADD PRIMARY KEY (`QuestionID`),
  ADD KEY `CategoryID` (`CategoryID`),
  ADD KEY `TypeID` (`TypeID`),
  ADD KEY `CreatorID` (`CreatorID`);

--
-- Indexes for table `typequestion`
--
ALTER TABLE `typequestion`
  ADD PRIMARY KEY (`TypeID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `account`
--
ALTER TABLE `account`
  MODIFY `ID` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `answer`
--
ALTER TABLE `answer`
  MODIFY `AnswerID` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT for table `categoryquestion`
--
ALTER TABLE `categoryquestion`
  MODIFY `CategoryID` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

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
-- AUTO_INCREMENT for table `typequestion`
--
ALTER TABLE `typequestion`
  MODIFY `TypeID` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `answer`
--
ALTER TABLE `answer`
  ADD CONSTRAINT `answer_ibfk_1` FOREIGN KEY (`QuestionID`) REFERENCES `question` (`QuestionID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `exam`
--
ALTER TABLE `exam`
  ADD CONSTRAINT `exam_ibfk_1` FOREIGN KEY (`CategoryID`) REFERENCES `categoryquestion` (`CategoryID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `exam_ibfk_2` FOREIGN KEY (`CreatorID`) REFERENCES `account` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `examquestion`
--
ALTER TABLE `examquestion`
  ADD CONSTRAINT `examquestion_ibfk_1` FOREIGN KEY (`ExamID`) REFERENCES `exam` (`ExamID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `examquestion_ibfk_2` FOREIGN KEY (`QuestionID`) REFERENCES `question` (`QuestionID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `question`
--
ALTER TABLE `question`
  ADD CONSTRAINT `question_ibfk_1` FOREIGN KEY (`CategoryID`) REFERENCES `categoryquestion` (`CategoryID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `question_ibfk_2` FOREIGN KEY (`TypeID`) REFERENCES `typequestion` (`TypeID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `question_ibfk_3` FOREIGN KEY (`CreatorID`) REFERENCES `account` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
