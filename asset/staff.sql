CREATE TABLE `staff` (
  `USERNAME` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `PASSWORD` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `FIRST_NAME` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `LAST_NAME` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `PERMISSION` varchar(50) COLLATE utf8_unicode_ci NOT NULL
) ENGINE = InnoDB DEFAULT CHARSET = utf8 COLLATE = utf8_unicode_ci;
--
-- Dumping data for table `staff`
--
INSERT INTO `staff` (
    `USERNAME`,
    `PASSWORD`,
    `FIRST_NAME`,
    `LAST_NAME`,
    `PERMISSION`
  )
VALUES (
    'admin',
    'passall',
    'สุขดี',
    'ชาวกรุงเทพ',
    'ADMIN'
  ),
  (
    'bigbro',
    'bigpass',
    'ราดหน้า',
    'เส้นใหญ่',
    'EMPLOYEE'
  ),
  (
    'sss',
    '123',
    'เส้นเล็ก',
    'ไม่งอก',
    'EMPLOYEE'
  );
  
ALTER TABLE `staff`
ADD PRIMARY KEY (`USERNAME`);
COMMIT;