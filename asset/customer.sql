CREATE TABLE `customer` (
  `CUST_NUM` int(4) NOT NULL,
  `CUST_FNAME` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `CUST_LNAME` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `CUST_ADDR` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `CUST_PHONE` varchar(50) COLLATE utf8_unicode_ci NOT NULL
) ENGINE = InnoDB DEFAULT CHARSET = utf8 COLLATE = utf8_unicode_ci;
--
-- Dumping data for table `customer`
--
INSERT INTO `customer` (
    `CUST_NUM`,
    `CUST_FNAME`,
    `CUST_LNAME`,
    `CUST_ADDR`,
    `CUST_PHONE`
  )
VALUES (1002, 'สมชาย', 'สุขดี', 'กรุงเทพฯ', '0-2503-2121'),
  (
    1028,
    'สิทธิชัย',
    'สมานกุล',
    'นนทบุรี',
    '0-6698-1250'
  ),
  (
    1247,
    'กุมภา',
    'พิทักษ์สุข',
    'กรุงเทพฯ',
    '0-9274-5812'
  ),
  (
    1321,
    'งามเนตร',
    'ถนอมศรี',
    'นนทบุรี',
    '0-6689-3472'
  ),
  (
    2081,
    'สวัสดิ์',
    'สุขมาก',
    'ปทุมธานี',
    '0-2576-2348'
  ),
  (
    2102,
    'สกุลรัตน์',
    'สมควรสุข',
    'กรุงเทพฯ',
    '0-2281-1576'
  ),
  (
    2306,
    'ลัดดาวรรณ',
    'เก่งเรียน',
    'นนทบุรี',
    '0-1496-3294'
  ),
  (
    3006,
    'ลัดดา',
    'งามนัก',
    'ปทุมธานี',
    '0-5503-4932'
  ),
  (
    3013,
    'สกุณา',
    'เรียนเก่ง',
    'นนทบุรี',
    '0-1723-1250'
  ),
  (
    3287,
    'กรุณา',
    'นามไพร',
    'นนทบุรี',
    '0-7555-0332'
  );

ALTER TABLE `customer`
ADD PRIMARY KEY (`CUST_NUM`);
COMMIT;