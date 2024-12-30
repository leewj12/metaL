import React, { useState } from "react";
import { Calendar, dateFnsLocalizer } from "react-big-calendar";
import "react-big-calendar/lib/css/react-big-calendar.css";
import styles from "../css/attendance.module.css";
import format from "date-fns/format";
import parse from "date-fns/parse";
import startOfWeek from "date-fns/startOfWeek";
import getDay from "date-fns/getDay";
import { enUS } from "date-fns/locale";

const locales = {
  "en-US": enUS,
};

const localizer = dateFnsLocalizer({
  format,
  parse,
  startOfWeek,
  getDay,
  locales,
});

export default function AttendanceWithLogs() {
  const [attendanceEvents, setAttendanceEvents] = useState([]);
  const [selectedDate, setSelectedDate] = useState(new Date());
  const [log, setLog] = useState([]);

  const handleAddEvent = (type) => {
    const dateKey = selectedDate.toDateString();

    // 중복 방지: 동일 날짜에 동일 이벤트 타입 제한
    const isDuplicate = attendanceEvents.some(
      (event) => event.start.toDateString() === dateKey && event.type === type
    );

    if (!isDuplicate) {
      const newEvent = {
        title: type,
        start: selectedDate,
        end: selectedDate,
        type,
      };

      setAttendanceEvents((prevEvents) => [...prevEvents, newEvent]);

      // 로그 기록 추가
      setLog((prevLog) => [
        ...prevLog,
        `[${format(selectedDate, "yyyy-MM-dd")}] ${type}: ${new Date().toLocaleTimeString()}`,
      ]);
    }
  };

  const eventStyleGetter = (event) => {
    let backgroundColor = "#4caf50"; // Default 입실
    if (event.type === "퇴실") backgroundColor = "#ff5722";

    const style = {
      backgroundColor,
      color: "white",
      borderRadius: "5px",
      padding: "5px",
      textAlign: "center",
    };
    return { style };
  };

  const dayPropGetter = (date) => {
    const day = date.getDay(); // 0 (일요일) ~ 6 (토요일)
    if (day === 0) {
      return {
        style: {
          backgroundColor: "#ffdddd", // 빨간색 배경
        },
      };
    } else if (day === 6) {
      return {
        style: {
          backgroundColor: "#ddddff", // 파란색 배경
        },
      };
    }
    return {};
  };

  return (
    <div className={styles.container}>
      <h1>출석 관리</h1>

      <div className={styles.calendarContainer}>
        <Calendar
          localizer={localizer}
          events={attendanceEvents}
          startAccessor="start"
          endAccessor="end"
          style={{ height: 600, margin: "50px" }}
          onSelectSlot={(slotInfo) => setSelectedDate(slotInfo.start)}
          selectable
          eventPropGetter={eventStyleGetter}
          dayPropGetter={dayPropGetter}
        />
      </div>

      <div className={styles.buttonContainer}>
        <button
          className={`${styles.button} ${styles.entryButton}`}
          onClick={() => handleAddEvent("입실")}
        >
          입실
        </button>
        <button
          className={`${styles.button} ${styles.exitButton}`}
          onClick={() => handleAddEvent("퇴실")}
        >
          퇴실
        </button>
      </div>

      <div className={styles.logContainer}>
        <h3>출석 로그</h3>
        <ul>
          {log.length > 0 ? (
            log.map((entry, index) => <li key={index}>{entry}</li>)
          ) : (
            <p>출석 기록이 없습니다.</p>
          )}
        </ul>
      </div>
    </div>
  );
}
