"use client";

import { components } from "@/lib/backend/apiV1/schema";
import client from "@/lib/backend/client";
import { useEffect, useState } from "react";

export default function ClientPage() {
  const [me, setMe] = useState<components["schemas"]["MemberDto"] | null>(null);

  const loadMe = async () => {
    const response = await client.GET("/api/v1/members/me");
    response.data && setMe(response.data);
  };

  useEffect(() => {
    loadMe();
  }, []);

  if (me == null) return <>로딩중</>;

  return (
    <div>
      <div>ID : {me.id}</div>
      <div>가입 : {me.createDate}</div>
      <div>수정 : {me.modifyDate}</div>
      <div>별명 : {me.nickname}</div>
    </div>
  );
}
