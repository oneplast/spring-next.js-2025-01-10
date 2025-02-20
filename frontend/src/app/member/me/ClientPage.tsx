"use client";

import { components } from "@/lib/backend/apiV1/schema";
import client from "@/lib/backend/client";
import { useEffect, useState } from "react";

export default function ClientPage({
  me,
}: {
  me: components["schemas"]["MemberDto"];
}) {
  return (
    <div>
      <div>ID : {me.id}</div>
      <div>가입 : {me.createDate}</div>
      <div>수정 : {me.modifyDate}</div>
      <div>별명 : {me.nickname}</div>
    </div>
  );
}
