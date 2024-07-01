DELETE FROM INCIDENTS;

insert into incidents(id, closed_at, created_at, description, name, updated_at, status) values
(2, null, '2024-06-28 20:11:17', 'Fiber optic break due to elevated vehicle passage', 'Fiber optic breakage', null, 'OPEN'),
(3, null, '2024-06-28 20:12:17', 'Power outage caused by storm', 'Storm Power Outage', null, 'OPEN'),
(4, null, '2024-06-28 20:13:17', 'Server downtime due to maintenance', 'Maintenance Downtime', null, 'OPEN'),
(5L, null, '2024-06-28 20:14:17', 'Unauthorized access attempt', 'Security Breach', null, 'CLOSED'),
(6, null, '2024-06-28 20:15:17', 'Network latency issues', 'Network Latency', null, 'OPEN'),
(7, null, '2024-06-28 20:16:17', 'Database connection error', 'DB Connection Error', null, 'OPEN'),
(8, null, '2024-06-28 20:17:17', 'Application crash', 'App Crash', null, 'OPEN'),
(9, null, '2024-06-28 20:18:17', 'Disk space threshold exceeded', 'Disk Space Alert', null, 'OPEN'),
(10L, null, '2024-06-28 20:19:17', 'Memory leak detected', 'Memory Leak', null, 'OPEN'),
(11, null, '2024-06-28 20:20:17', 'Hardware failure', 'Hardware Failure', null, 'OPEN'),
(12, null, '2024-06-28 20:21:17', 'Service interruption due to DDoS attack', 'DDoS Attack', null, 'OPEN'),
(13, null, '2024-06-28 20:22:17', 'Configuration file missing', 'Config Missing', null, 'OPEN'),
(14, null, '2024-06-28 20:23:17', 'SSL certificate expired', 'SSL Expired', null, 'OPEN'),
(15, null, '2024-06-28 20:24:17', 'Backup failure', 'Backup Failure', null, 'OPEN'),
(16, null, '2024-06-28 20:25:17', 'High CPU usage detected', 'High CPU Usage', null, 'OPEN'),
(17, null, '2024-06-28 20:26:17', 'Service restart required', 'Service Restart', null, 'OPEN'),
(18, null, '2024-06-28 20:27:17', 'Network congestion', 'Network Congestion', null, 'OPEN'),
(19, null, '2024-06-28 20:28:17', 'Unauthorized configuration change', 'Config Change Unauthorized', null, 'OPEN'),
(20, null, '2024-06-28 20:29:17', 'Email delivery issues', 'Email Issues', null, 'OPEN'),
(21, null, '2024-06-28 20:30:17', 'File system corruption detected', 'File System Corruption', null, 'OPEN'),
(22, null, '2024-06-28 20:31:17', 'Unexpected server shutdown', 'Server Shutdown', null, 'OPEN');