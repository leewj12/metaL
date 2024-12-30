import axios from 'axios';

const API_BASE_URL = 'http://localhost:8091'; // Spring Boot 서버 주소

export const login = async (username, password) => {
    const response = await axios.post(
        `${API_BASE_URL}/login`,
        { username, password },
        { withCredentials: true } // 세션 쿠키를 허용
    );
    return response.data;
};

export const logout = async () => {
    await axios.post(`${API_BASE_URL}/logout`, {}, { withCredentials: true });
};

export const fetchProtectedData = async () => {
    const response = await axios.get(`${API_BASE_URL}/api/protected`, {
        withCredentials: true,
    });
    return response.data;
};