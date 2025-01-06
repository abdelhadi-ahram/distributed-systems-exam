"use client"
import { HomeIcon, Squares2X2Icon } from "@heroicons/react/24/outline";
import { Button } from "@/components/ui/button";
import { Logo } from "@/components/ui/logo";
import UserDropdown from "./user-dropdown";
import Link from "next/link";
import { ScrollText, Users } from "lucide-react";

function SearchIcon(props: any) {
  return (
    <svg
      {...props}
      xmlns="http://www.w3.org/2000/svg"
      width="24"
      height="24"
      viewBox="0 0 24 24"
      fill="none"
      stroke="currentColor"
      strokeWidth="2"
      strokeLinecap="round"
      strokeLinejoin="round"
    >
      <circle cx="11" cy="11" r="8" />
      <path d="m21 21-4.3-4.3" />
    </svg>
  )
}

export default function Sidebar() {

  return (
    <div className="fixed top-0 inset-x-0 z-40 animate-fade-in backdrop-blur-[12px] [--animation-delay:600ms] md:left-0 md:inset-y-0 md:w-16 flex md:flex-col items-center justify-between px-3 md:px-2 py-2 md:py-3 border-r bg-background">
      <div className="flex md:flex-col items-center">
        <Logo />
        <Button variant="ghost" className="md:hidden ml-3 p-2 rounded-lg" >
          <Squares2X2Icon className="h-6 w-6 text-gray-500" />
          <span className="sr-only">Open Sidebar</span>
        </Button>
        <Button variant="ghost" className="md:mt-6 p-2 rounded-lg" asChild >
          <Link href="/customers">
            <Users className="h-6 w-6 text-gray-500" />
            <span className="sr-only">Customer</span>
          </Link>
        </Button>
        <Button variant="ghost" size="icon" className="md:mt-3 p-2 rounded-lg" asChild>
          <Link href="/transfers">
            <ScrollText className="h-5 w-5 text-gray-500" />
            <span className="sr-only">transfers</span>
          </Link>
        </Button>
      </div>

      <div className="flex flex-col items-center space-y-3">
        <UserDropdown />
      </div>
    </div>
  )
}