package com.siddhrans.biometric.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Simple FTP fetcher service that downloads a file from an FTP server and
 * stores it as a temporary File. This is intentionally lightweight — it
 * returns the downloaded File and leaves further processing to caller.
 *
 * TODOs:
 * - Add SFTP support (use SSHJ or JSch) for secure transfers.
 * - Add retry/backoff, timeouts, and configurable passive/active mode.
 * - Add validation and checksum verification.
 */
@Service
public class FtpFetcherService {

    private static final Logger logger = LoggerFactory.getLogger(FtpFetcherService.class);

    public File fetch(String host, int port, String username, String password, String remotePath) throws IOException {
        FTPClient client = new FTPClient();
        File tempFile = null;
        try {
            client.connect(host, port);
            boolean loggedIn = client.login(username, password);
            if (!loggedIn) {
                throw new IOException("FTP login failed for user: " + username);
            }

            client.enterLocalPassiveMode();
            client.setFileType(FTP.BINARY_FILE_TYPE);

            // create a temp file
            String baseName = new File(remotePath).getName();
            tempFile = File.createTempFile("biometric_" + baseName + "_", ".dat");

            try (FileOutputStream fos = new FileOutputStream(tempFile)) {
                boolean success = client.retrieveFile(remotePath, fos);
                if (!success) {
                    throw new IOException("Failed to retrieve remote file: " + remotePath);
                }
            }

            logger.info("FTP fetch successful: {} -> {}", remotePath, tempFile.getAbsolutePath());
            return tempFile;
        } finally {
            if (client.isConnected()) {
                try {
                    client.logout();
                    client.disconnect();
                } catch (IOException e) {
                    logger.warn("Error while disconnecting FTP client", e);
                }
            }
        }
    }
}
