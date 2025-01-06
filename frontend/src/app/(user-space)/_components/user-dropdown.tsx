"use client"
import { useTheme } from 'next-themes'

import Image from 'next/image'
import {
  DropdownMenu,
  DropdownMenuContent,
  DropdownMenuGroup,
  DropdownMenuItem,
  DropdownMenuSeparator,
  DropdownMenuTrigger,
} from '@/components/ui/dropdown-menu'

import { LaptopIcon, MoonIcon, SunIcon } from '@radix-ui/react-icons'
import { Button } from '@/components/ui/button'
import { ArrowLeftStartOnRectangleIcon, CheckIcon } from '@heroicons/react/24/outline'
import { User } from 'lucide-react'
import Link from 'next/link'

const ICONS: Record<string, any> = {
  'light': <SunIcon className="mr-2 h-4 w-4" />,
  'dark': <MoonIcon className="mr-2 h-4 w-4" />,
  'system': <LaptopIcon className="mr-2 h-4 w-4" />
}

interface UserDropdownProps {
  className?: string
}

export default function UserDropdown({ className }: UserDropdownProps) {
  const { themes, theme, setTheme } = useTheme()

  return (
    <DropdownMenu>
      <DropdownMenuTrigger asChild>
        <Button variant="ghost" size="icon" className="rounded-full">
          <Image src="/placeholder.svg" width="32" height="32" className="rounded-full" alt="Avatar" />
          <span className="sr-only">Toggle user menu</span>
        </Button>
      </DropdownMenuTrigger>
      <DropdownMenuContent className="w-56" align="end" forceMount>
        <div className='space-y-0.5 px-2'>
          <p className='text-sm'>user.username</p>
          <p className='text-xs text-muted-foreground'>user@gmail.com</p>
        </div>
        <DropdownMenuSeparator />
        <DropdownMenuItem asChild>
          <Link href='/account'>
            <User className="mr-2 h-4 w-4" />
            <span>Profile</span>
          </Link>
        </DropdownMenuItem>
        <DropdownMenuSeparator />
        <DropdownMenuGroup>
          {themes.map((item: string, index: number) => (
            <DropdownMenuItem key={index} className="flex items-center justify-between" onClick={() => setTheme(item)}>
              <div className="flex items-center">
                {ICONS[item] ?? <></>}
                <span>{item}</span>
              </div>
              {theme === item && (
                <CheckIcon className="h-4 w-4 text-foreground" />
              )}
            </DropdownMenuItem>
          ))}
        </DropdownMenuGroup>
        <DropdownMenuSeparator />
        <DropdownMenuItem>
          <ArrowLeftStartOnRectangleIcon className="mr-2 h-4 w-4" />
          <span>Log out</span>
        </DropdownMenuItem>
      </DropdownMenuContent>
    </DropdownMenu>
  )
}